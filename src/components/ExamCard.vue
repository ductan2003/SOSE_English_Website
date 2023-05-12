<template>
  <div class="exam-container">

    <div class="exam-image">
      <img :src='"http://localhost:8019/admin/exams/file/" + exam.fileImage'   alt="pic" class="imageabc"/>
    </div>

    <div class="text">
      <div class="card-header">
        <router-link class="card-title" :to="{ name: 'exam', params: { id: exam.id } }">{{ exam.title }} </router-link>
        <p class="card-description">Category: {{exam.category.name}}</p>
        <p class="card-description">Year: {{exam.year}}</p>
        <p class="card-description">{{exam.description}}</p>
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
.card-title:hover{
  color: #4caf4f;

}
.card-header{
  width: 560px;
  display: flex;
  flex-direction: column;
}

.card-description{
  text-align: left;
  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 25px;
  margin-bottom: 0%;
  /* or 156% */

}
.imageabc{
  max-width: 145px;
  max-height: 145px;
  border-radius: 15%;
}
</style>