<template>
  <v-main>
    <v-container fluid fill-height>
      <v-layout align-center justify-center>
        <v-flex xs12 sm8 md4>
          <div>
            <v-alert
                v-if = "error_message"
              color="red"
              outlined
              text
              type="error"
           >
              {{error_message}}
            </v-alert>
          </div>

          <v-card-text>
            <v-form>
              <v-text-field prepend-icon="person" name="email" label="E-mail"
                            v-model="credentials.email"
                            type="text"></v-text-field>
              <v-text-field prepend-icon="lock" name="password" label="Password"
                            v-model="credentials.password"
                            type="password">
              </v-text-field>
              <v-text-field prepend-icon="lock" name="Confirm password" label="Confirm password"
                            v-model="credentials.confirmPassword"
                            type="password">
              </v-text-field>
              <v-text-field name="name" label="Name"
                            v-model="credentials.name"
                            type="text"></v-text-field>
              <v-text-field name="surname" label="Surname"
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
      </v-layout>
    </v-container>
  </v-main>
</template>

<script>
import {api} from "@/util/Api";
import {auth} from "@/util/Auth";

export default {
  name: 'RegisterView',

  data() {
    return {
      credentials: {
        name: "",
        surname: "",
        email: "",
        password: "",
        confirmPassword: "",
      },
      error_message: "",
    };
  },

  methods: {
    reg() {
      api.post(this, "/user", this.credentials, () => {
        auth.login(this, this.credentials);
      }, errorStatus => {
        if (errorStatus === 406) {
          this.passwordsDoNotMatch();
        }
        else if (errorStatus === 409) {
          this.accountAlreadyExists();
        }
      });
    },

    back() {
      this.$router.push("/");
    },

    passwordsDoNotMatch() {
      this.error_message = "Podane hasła nie zgadzają się."
    },

    accountAlreadyExists() {
      this.error_message = "Dany użytkownik już istnieje."
    }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
