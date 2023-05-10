<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <br />
        <br />

        <form @submit.prevent="forgotPassword()">
          <h1 class="loginHeader">Quên mật khẩu</h1>

          <br />

          <div class="form-floating mb-3">
            <input
                class="form-control"
                id="floatingUsername"
                placeholder="username"
                name="username"
                v-model="username"
                required
            />
            <label for="floatingUsername">Tên đăng nhập</label>
          </div>

          <div class="d-grid gap-2">
            <button type="button" class="Send-email" v-on:click="forgotPassword()">
              Send email
            </button>
          </div>



          <hr />

          <div>
            <router-link class="loginButton" type="button" to="/login"
            >Đăng nhập</router-link
            >
          </div>

          <br />
        </form>
        <br />
      </div>
      <div class="col-md-4"></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import {toast} from "vue3-toastify";
export default {
  data() {
    return {
      username: "",
    }
  },
  methods: {
    async forgotPassword() {
      let url = "http://localhost:8019/api/auth/forgot-password";
      await axios
          .post(
              url, {username: this.username}, {
                headers: {
                  "Content-Type": "multipart/form-data",
                },
              }
          )
          .then((response) => {
            console.log(response.data);
            toast.success("Email sent", {position: toast.POSITION.BOTTOM_RIGHT}),
                {
                  autoClose: 1000,
                };
            this.$router.push({ path: "/login" });
          })
          .catch((error) => {
            console.log(error);
            toast.error("Cann't send email", {position: toast.POSITION.BOTTOM_RIGHT}),
                {
                  autoClose: 1000,
                };
          })
    }
  }
}
</script>

<style>
.loginHeader {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 35px;
  line-height: 28px;
  text-align: center;
}

.Send-email {
  color: white;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;

  width: inherit;
  height: 40px;

  background: #0d6efd;
  border-radius: 6px;
  text-align: center;

  text-decoration: none;
  font-family: "Inter";
  border-width: 0px;
}
.loginButton{
  color: white;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;

  width: inherit;
  height: 40px;

  background: #4caf4f;
  border-radius: 6px;
  text-align: center;

  text-decoration: none;
  font-family: "Inter";
}
</style>