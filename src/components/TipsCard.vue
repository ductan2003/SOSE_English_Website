<template>
  <div class="container">
    <div class="tips-image">
      <img src="@/assets/tips/Tips-image.png" alt="pic" />
    </div>
    <div class="tips-content">
      <div class="tips-title">
        <router-link class="title-header" :to="{ name: 'tip', params: { id: tip.title } }">{{ tip.title }} </router-link>
      </div>
      <div class="description">
        <p class="tip-description">{{ tip.title }}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "tip",
  props: ["tip"],
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: row-reverse;
  align-items: center;
  position: relative;
  padding-bottom: 60px;
  display: flex;
  flex-direction: row;
  padding-left: 15%;
}

.tips-image {
  border-radius: 30px;
}

.tips-content {
  padding-left: 2.7%;
  position: relative;
  width: 775.52px;
  height: 146px;
}

.title-header {
  text-align: left;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 28px;
  line-height: 40px;

  display: flex;
  align-items: center;

  color: #000000;
  text-decoration: none;
}

.description {
  position: absolute;
  width: 707.78px;
  height: 100px;
}

.tip-description {
  text-align: left;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 25px;
  /* or 156% */

  display: flex;
  align-items: center;

  color: #717171;
}
</style>
