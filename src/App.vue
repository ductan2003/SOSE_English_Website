<template>
  <Header/>
  <router-view />
  <Footer />
</template>

<script>
import Header from "./components/Header.vue";
import Footer from "./components/Footer.vue";
// import axios from "axios";
export default {
  components: {
    Header, Footer
  },

}

</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

nav {
  padding: 30px;
  font-weight: bolder;
}

nav a {
  /* font-weight: bold; */
  color: #2c3e50;
}

nav a.router-link-exact-active {
  color: #2c3e50;
}

nav a.router-link-exact-active:hover {
  color: #119f77;
}
</style>
