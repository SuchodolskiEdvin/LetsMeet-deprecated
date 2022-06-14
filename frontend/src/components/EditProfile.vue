<template>
  <v-main>
    <v-container fluid fill-height>
      <v-layout align-center justify-center>
        <v-flex xs12 sm8 md4>
          <v-card-text>
            <v-form>
              <v-text-field prepend-icon="person" name="email" label="E-mail"
                            v-model="data.email"
                            type="text"></v-text-field>
              <v-text-field name="name" label="Name"
                            v-model="data.name"
                            type="text"></v-text-field>
              <v-text-field name="surname" label="Surname"
                            v-model="data.surname"
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
                  <v-btn color="primary" @click="save">
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

export default {
  name: "EditProfile",

  data() {
    return {
      data: {
        name: "",
        surname: "",
        email: "",
      },
      error_message: "",
    };
  },

  methods: {
    back() {
      this.$router.push("/app");
    },
    save() {
      api.put(this, "/user", this.data, () => {
        this.$router.push("/app");
      });
    },
  },

  created() {
    api.get(this, "/user/edit", {}, data => {
      this.data = data;
    });
  },
}
</script>

<style scoped>

</style>