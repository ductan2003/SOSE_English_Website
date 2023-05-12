<template>

  <div class="container">
    <div class="heading-page">
      <p class="heading-page-content">Welcome, {{this.firstName}}</p>
    </div>

    <div class="body">
      <div class="avatar">
        <img src="@/assets/person3.jpg" alt="ava">
<!--        <img src={{this.fileImg}} alt="ava">-->
        <div class="button">
          <input @change="handleFileImg()" ref="fileImg" type="file" name="file" id="file" />
          <button @click="handleButtonClick" type="submit">Change your avatar</button>
        </div>
      </div>

      <div class="infoContainer">
      <div class="info">
        <div class="cardInfo">
          <p>First name:</p>
          <div class="box">
            <input v-model="firstName" type="text" />
          </div>
        </div>

        <div class="cardInfo">
          <p>Last name:</p>
          <div class="box">
            <input v-model="lastName" type="text" />
          </div>
        </div>

        <div v-if="auth" class="cardInfo">
          <p>Password:</p>
          <div class="box">
            <input v-model="password" type="text" />
          </div>
        </div>

        <div v-if="auth" class="cardInfo">
          <p>New Password:</p>
          <div class="box">
            <input v-model="newPassword" type="text" />
          </div>
        </div>

        <div v-if="auth" class="cardInfo">
          <p>Confirm Password:</p>
          <div class="box">
            <input v-model="confirmPassword" type="text" />
          </div>
        </div>
      </div>
        <div class="button">
        <button type="button" class="saveButton" v-on:click="saveUserInfo">Save</button>
        <button v-if="!auth" type="button" class="changeButton" v-on:click="changePass">Change Password</button>
          <button v-if="auth" type="button" class="changeButton" v-on:click="undoChangePass">Back</button>
        </div>
        </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
axios.defaults.headers.common[ "Authorization"] = `Bearer ` + localStorage.getItem("token");

export default {
  data() {
    return {
      user: null,
      fileImg: null,
      firstName: "",
      lastName: "",
      password: "",
      newPassword: "",
      confirmPassword: "",
      auth: false,
    }
  },
  methods: {
    handleFileImg(){
      this.fileImg = this.$refs.fileImg.files[0];
      console.log(this.fileImg);
    },
    handleButtonClick(){
      this.$refs.fileImg.click();
    },
    saveUserInfo() {

    },
    async getUser() {
      if (localStorage.getItem("token")) {
        axios.defaults.headers.common[ "Authorization"] = `Bearer ` + localStorage.getItem("token");
        const response = await axios.get("http://localhost:8019/api/auth/profile");
        this.user = response.data;
        console.log(this.user);
      }
    },
    async getUserInfo() {
      await this.getUser();
      const response = await axios.get("http://localhost:8019/account/" + this.user.username);
      this.firstName = response.data.firstName;
      this.lastName = response.data.lastName;
    },
    changePass() {
      this.auth = true;
    },
    undoChangePass() {
      this.auth = false;
    }
  },
  beforeMount() {
    this.getUserInfo();
  }

}
</script>

<style scoped>
.container {
  gap: 30px;
  padding-bottom: 20px;
}
.heading-page {
  position: relative;
  top: 15px;
  height: auto;
  align-items: center;
  justify-content: center;
  /*padding-bottom: 0px;*/

  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 64px;
  line-height: 75px;

  color: #4caf4f;
}
.body{
  display: flex;
  flex-direction: row;
  /*margin-left: 10%;*/
  margin-top: 5%;
  gap: 80px;
  justify-content: center;

}
.avatar{
  display: flex;
  flex-direction: column;
  gap: 10px;
  align-items: center;
}
.avatar img{
  width: 200px;
  height: 200px;
  border-radius: 100%;
}
.avatar button{
  border-radius: 6px;
  font-family: 'Inter';
  font-style: normal;
  font-weight: 500;
  font-size: 14px;
  line-height: 20px;
  /* identical to box height, or 143% */
  color: #000000;
  border: none;

  width: fit-content;
  height: 30px;
  justify-content: center;
  background: #D9D9D9;
  /*color: white;*/
}
input[type="file"] {
  display: none;
}
.info{
  display: flex;
  flex-direction: column;
  width: fit-content;
  gap: 15px;
  align-items: flex-end;
}
.cardInfo{
  display: flex;
  flex-direction: row;
  align-items: center;
  gap: 20px;
}
.cardInfo p{
  font-family: 'Inter';
  font-style: normal;
  font-weight: 500;
  font-size: 18px;
  line-height: 28px;
  margin-bottom: 0px;
  width: fit-content;
}
.box input{
  background: transparent;
  flex: 1;
  border: 0;
  outline: none;
  padding: 2% 3%;
  font-family: 'Inter';
}
.box {
  box-sizing: border-box;
  height: 35px;
  display: flex;
  align-items: center;
  padding: 5px 10px;
  background: #D9DBE1;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
}
.saveButton{
  color: white;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;

  width: 80px;
  height: 40px;

  background: #0d6efd;
  border-radius: 30px;
  text-align: center;

  text-decoration: none;
  font-family: "Inter";
  border-width: 0px;
  align-items: center;
}
.infoContainer{
  gap: 25px;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.changeButton{
  color: white;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;

  width: 170px;
  height: 40px;

  background: #4CAF4F;
  border-radius: 30px;
  text-align: center;

  text-decoration: none;
  font-family: "Inter";
  border-width: 0px;
  align-items: center;
}
.button {
  display: flex;
  flex-direction: column;
  gap: 10px;
  align-items: center;
}
</style>