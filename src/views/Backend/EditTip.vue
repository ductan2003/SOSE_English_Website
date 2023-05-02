<template>
  <div class="container">
    <div class="heading-page">
      <p class="heading-page-content">Admin - Edit Tips</p>
    </div>
    <br>

  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "EditTIp.vue",
  data() {
    return {
      tips: [],
    };
  },
  methods: {
    getTips() {
      axios
          .get("http://localhost:8019/admin/all")
          .then((response) => {
            console.log(response.data);
            this.tips = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
    },
  },
  beforeMount() {
    this.getTips();
  },
}
</script>

<style scoped>

.heading-page {
  position: relative;
  top: 15px;
  height: 100px;
  align-items: center;
  justify-content: center;
  padding-bottom: 20px;

  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 64px;
  line-height: 75px;

  color: #4caf4f;
}
</style>