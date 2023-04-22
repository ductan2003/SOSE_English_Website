<template>
  <Header />
  <router-view />
  <Footer/>
</template>

<script setup>
  import Header from './components/Header.vue';
  import Footer from './components/Footer.vue';

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
