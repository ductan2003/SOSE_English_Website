<template>
  <div class="container">
    <div class="heading-page">
      <p class="heading-page-content">IELTS Tips</p>
    </div>
    <br>
    <div class="content">
      <Tipscard v-for="tip in tips" :key="tip.id" :tip="tip"/>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import Tipscard from "../components/TipsCard.vue";
export default {
  components: {
    Tipscard,
  },
  name: "Tipslist",
  data() {
    return {
      tips: [],
    };
  },
  methods: {
    getTips() {
      axios
        .get("http://localhost:8019/admin/test/reading")
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
};
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

.content-page {
  align-items: center;
  height: 500px;
  position: relative;
}
</style>
