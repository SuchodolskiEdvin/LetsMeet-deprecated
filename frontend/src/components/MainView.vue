<template>
  <div>
    <v-card-actions>
      <v-btn color="primary" @click="create">
        Create
      </v-btn>
    </v-card-actions>
  </div>
</template>

<script>
import {api} from "@/util/Api";

export default {
  name: "MainView",

  data() {
    return {
      meets: [],
    }
  },

  methods: {
    create() {
      this.$router.push("/create-meet");
    }
  },

  created() {
    api.get(this, "/meet", {}, data => {
      console.log(data);
      this.meets = data;
    });
  }
}
</script>

<style scoped>

</style>