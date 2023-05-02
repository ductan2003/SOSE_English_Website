<template>
<!--  <p>Exam</p>-->
  <div class="exam-container">
    <br>
    <h1>Exam {{ $route.params.id}}</h1>
    <iframe src="/pdf/test1.pdf" class="pdf"></iframe>
    <br>
  </div>
</template>

<script>

</script>

<style scoped>
.exam-container{
  display: flex;
  flex-direction: column;
}
.pdf{
  width: 50%;
  height: 600px;
}
</style>