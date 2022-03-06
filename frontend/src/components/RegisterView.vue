<template>
  <v-main>
    <v-container fluid fill-height>
      <v-layout align-center justify-center>
        <v-flex md4>
          <div>
            <v-alert
                v-if="error_message"
                color="red"
                outlined
                text
                type="error"
            >
              {{ error_message }}
            </v-alert>
          </div>

          <v-card-text>
            <v-form>
              <v-text-field prepend-icon="person" name="email" label="E-mail"
                            v-model="credentials.email"
                            :rules="[rules.required]"
                            type="text"></v-text-field>
              <v-text-field prepend-icon="lock" name="password" label="Password"
                            :append-icon="show1 ? 'visibility' : 'visibility_off'"
                            :rules="[rules.required]"
                            :type="show1 ? 'text' : 'password'"
                            value="Pa"
                            @click:append="show1 = !show1"
                            v-model="credentials.password"
                            @focus="magic_flag = true"
                            @blur="magic_flag = false"
                            v-popover:test.top
                            >
              </v-text-field>
              <v-text-field prepend-icon="lock" name="Confirm password" label="Confirm password"
                            :append-icon="show2 ? 'visibility' : 'visibility_off'"
                            :rules="[rules.required]"
                            :type="show2 ? 'text' : 'password'"
                            @click:append="show2 = !show2"
                            v-model="credentials.confirmPassword">
              </v-text-field>
              <v-text-field name="name" label="Name"
                            :rules="[rules.required]"
                            v-model="credentials.name"
                            type="text"></v-text-field>
              <v-text-field name="surname" label="Surname"
                            :rules="[rules.required]"
                            v-model="credentials.surname"
                            type="text">
              </v-text-field>
            </v-form>

          </v-card-text>
          <v-card-actions>
            <v-row>

              <v-flex class="justify-space-between d-flex ma-4">
                <v-btn color="primary" @click="back">
                  Back
                </v-btn>
                <v-spacer></v-spacer>
                <v-btn color="primary" @click="reg">
                  Save
                </v-btn>
              </v-flex>
            </v-row>
          </v-card-actions>
        </v-flex>
        <popover name="test" id="popover">
          <v-card-text class="input_container" v-if="magic_flag" :style="{width: '200px'}">
            <v-flex align-start justify-end>
            <h4>Password must meet the following requirements:</h4>
            <ul>
              <li v-bind:class="{ is_valid: passwordMeterParams.contains_eight_characters }">8 Characters</li>
              <li v-bind:class="{ is_valid: passwordMeterParams.contains_number }">Contains Number</li>
              <li v-bind:class="{ is_valid: passwordMeterParams.contains_uppercase }">Contains Uppercase and Lowercase</li>
              <li v-bind:class="{ is_valid: passwordMeterParams.contains_special_character }">Contains Special Character</li>
            </ul>

            <v-progress-linear
                :value="progress"
                :color="color"
                rounded
                height="7"
            ></v-progress-linear>
            </v-flex>
          </v-card-text>
        </popover>


      </v-layout>
    </v-container>
  </v-main>
</template>

<script>
import {api} from "@/util/Api";
import {auth} from "@/util/Auth";
import Vue from 'vue'
import Popover from 'vue-js-popover'
// import passwordMeter from "vue-simple-password-meter";

Vue.use(Popover)

export default {
  name: 'RegisterView',
  // components: {passwordMeter},
  data() {
    return {
      credentials: {
        name: "",
        surname: "",
        email: "",
        password: "",
        confirmPassword: "",
      },
      passwordMeterParams: {
        contains_eight_characters: false,
        contains_number: false,
        contains_uppercase: false,
        contains_special_character: false,
      },
      rules: {
        required: value => !!value || 'Required. ',
      },
      value: '',
      error_message: "",
      show1: false,
      show2: false,
      score: 0,
      strength: null,
      magic_flag: false,
    };
  },

  computed: {
    progress () {
      let score = 0;

      if(this.credentials.password.length >= 8) {
        score += 25;
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.passwordMeterParams.contains_eight_characters = true;
      } else {

        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.passwordMeterParams.contains_eight_characters = false;
      }

      if(this.credentials.password.toUpperCase() !== this.credentials.password &&
          this.credentials.password.toLowerCase() !== this.credentials.password) {
        score += 25;
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.passwordMeterParams.contains_uppercase = true;
      } else {

        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.passwordMeterParams.contains_uppercase = false;
      }

      if(/\d/.test(this.credentials.password)) {
        score += 25;
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.passwordMeterParams.contains_number = true;
      } else {

        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.passwordMeterParams.contains_number = false;
      }

      if(/[ `!@#$%^&*()_+\-=[\]{};':"\\|,.<>/?~]/.test(this.credentials.password)){
        score += 25;
        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.passwordMeterParams.contains_special_character = true;
      } else {

        // eslint-disable-next-line vue/no-side-effects-in-computed-properties
        this.passwordMeterParams.contains_special_character = false;
      }

      return score;
    },
    color () {
      return ['error', 'warning', 'success'][Math.floor(this.progress / 40)]
    },
  },

  methods: {
    reg() {
      if(this.credentials.name === "" ||
          this.credentials.surname === "" ||
          this.credentials.email === "" ||
          this.credentials.password === "" ||
          this.credentials.confirmPassword === "") {
        this.lackOfData();
      } else {
        api.post(this, "/user", this.credentials, () => {
          auth.login(this, this.credentials);
        }, errorStatus => {
          if (errorStatus === 406) {
            this.passwordsDoNotMatch();
          } else if (errorStatus === 409) {
            this.accountAlreadyExists();
          } else if (errorStatus === 403) {
            this.incorrectEmail();
          }
        });
      }
    },

    back() {
      this.$router.push("/");
    },

    passwordsDoNotMatch() {
      this.error_message = "Podane hasła nie zgadzają się."
    },

    accountAlreadyExists() {
      this.error_message = "Dany użytkownik już istnieje."
    },

    incorrectEmail() {
      this.error_message = "Błąd w etykiecie E-mail."
    },

    lackOfData() {
      this.error_message = "Brak danych."
    },
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
/* Basic Config --------- */


ul {
  padding-left: 20px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

li {
  margin-bottom: 8px;
  color: #525f7f;
  position: relative;
  text-align: left;
}

/* Checkmark & Strikethrough --------- */

.is_valid { color: rgba(136, 152, 170, 0.8);
  text-decoration: line-through;
  color: #b10000;
}
.is_valid:before { width: 100%; }

@keyframes draw {
  to { stroke-dashoffset: 0; }
}

[data-popover='test'] {
  width: auto !important;
  margin-left: 15px;
}

@media (max-width: 960px) and (min-width: 700px){
  .layout > .flex{
  padding: 10vw;
}
}

</style>
