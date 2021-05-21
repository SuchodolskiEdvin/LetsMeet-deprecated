<template>
  <v-app>
    <router-view/>
    <div>
      <v-footer app color="blue">
        <div style="width: 100%;">
          <div style="float: left">
            <span class="one--text"> &copy; Let's Meet</span>
          </div>
        </div>
      </v-footer>
    </div>
  </v-app>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
