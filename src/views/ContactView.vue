<template>
  <div class="container">
    <div class="person">
      <img src="@/assets/person1.png" alt="avatar" class="avatar"/>
      <div class="name">
        <p class="name-text">Tân Đức</p>
        <p class="email-text">21020392@vnu.edu.vn</p>
      </div>
    </div>

    <div class="person">
      <img src="@/assets/person2.png" alt="avatar" />
      <div class="name">
        <p class="name-text">Hà Nguyễn</p>
        <p class="email-text">haha@vnu.edu.vn</p>
      </div>
    </div>

    <div class="person">
      <img src="@/assets/person1.png" alt="avatar" />
      <div class="name">
        <p class="name-text">Đăng Dương</p>
        <p class="email-text">dangduong@vnu.edu.vn</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Contact"
}
</script>

<style scoped>
.container{
  display: flex;
  flex-direction: row;
  gap: 90px;
  align-content: center;
  justify-content: center;
  padding-top: 5%;
}
.avatar{
  border-radius: 100%;
  max-height: 200px;
  max-width: 200px;
}
.person{
  width: 200px;
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-bottom: 5%;
  gap: 20px;
}
.name{
  width: fit-content;
  height: auto;
  text-align: center;

}
.name-text{
  font-family: 'Inter';
  font-style: normal;
  font-weight: 500;
  font-size: 24px;
  line-height: 28px;

  color: #000000;
}
.email-text{
  font-family: 'Inter';
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 28px;

  color: #717171;
}
</style>