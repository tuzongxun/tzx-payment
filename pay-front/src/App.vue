<!--suppress JSAnnotator -->
<template>
  <div id="app">
    <!--<img src="./assets/logo.png">-->
    <!--<router-view/>-->
    <shophead></shophead>
  </div>
</template>

<script>
  import shophead from './components/shophead.vue'
export default {
  name: 'App',
  components: {
    'shophead': shophead
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  /*margin-top: 60px;*/
}
body{
  margin: 0px;
  padding: 0px;
  background-color: rgba(255,253,251,0.64);
}
</style>
