<template>
  <div class="exam-container">
    <div class="exam-image">
      <img src="@/assets/tips/Tips-image.png" alt="pic" />
    </div>

    <div class="text">
      <div class="card-header">
        <router-link class="card-title" :to="{ name: 'exam', params: { id: exam.title } }">{{ exam.title }} </router-link>
        <p class="card-description">Category: {{exam.category.name}}</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "exam",
  props: ["exam"],
};
</script>

<style>
.exam-container{
  display: flex;
  flex-direction: row;
  gap: 4%;
  padding-bottom: 5%;
}

.card-title{
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
.card-header{
  width: 560px;
  display: flex;
  flex-direction: column;
  gap: 5px;
}

.card-description{
  text-align: left;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 25px;
  /* or 156% */

  color: #717171;
}
</style>