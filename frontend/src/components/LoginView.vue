<template>
    <v-container fluid fill-height>
      <v-layout align-center justify-center>
        <v-flex xs12 sm8 md4>
          <v-card class="elevation-12" v-on:keyup.enter="login">
            <v-toolbar color="primary">
              <v-row>
                <v-col cols="12" style="display: block">
                  <strong class="ml-5" style="font-size: 18px">Let's meet</strong>
                </v-col>
              </v-row>
            </v-toolbar>
            <v-card-text>
              <v-form>
                <v-text-field prepend-icon="person" name="email" label="E-mail"
                              v-model="credentials.email"
                              type="text"></v-text-field>
                <v-text-field prepend-icon="lock" name="password"
                              label="Password"
                              v-model="credentials.password"
                              id="password"
                              autocomplete="on"
                              type="password">
                </v-text-field>
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-row>
                <v-flex class="justify-space-between d-flex ma-4">
                    <v-btn color="primary" @click="reg">
                      Sign up
                    </v-btn>
                    <v-spacer></v-spacer>
                    <v-btn color="primary" @click="login">
                      Log in
                    </v-btn>
                </v-flex>
              </v-row>

            </v-card-actions>
          </v-card>
        </v-flex>
      </v-layout>
    </v-container>
</template>

<script>
import {auth} from "@/util/Auth";

export default {
  name: 'LoginView',

  data() {
    return {
      credentials: {
        email: "",
        password: "",
      },
      error_message: "",
    };
  },

  methods: {
    async login() {
      await auth.login(this, this.credentials);
    },
    reg() {
      this.$router.push("/register");
    }
  },
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}
</style>
