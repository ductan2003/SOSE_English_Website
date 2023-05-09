<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <br />
        <br />

        <form @submit.prevent="Login()">
          <h1 class="loginHeader">Đăng nhập</h1>

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

          <div class="form-floating mb-3">
            <input
              type="password"
              class="form-control"
              id="floatingPass"
              placeholder="password"
              name="password"
              v-model="password"
              required
            />
            <label for="floatingPass" class="form-lable">Mật khẩu</label>
          </div>

          <div class="d-grid gap-2">
            <button type="button" class="login1" v-on:click="Login()">
              Đăng nhập
            </button>
          </div>

          <hr />

          <div>
            <router-link class="signup" type="button" to="/register"
              >Tạo tài khoản mới</router-link
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
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';

export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    async Login() {
      let url = "http://localhost:8019/api/auth/signin";
      await axios
        .post(
          url,
          {
            username: this.username,
            password: this.password,
          }, {
            headers: {
              "Content-Type": "multipart/form-data",
            }
            }
        )
        .then((response) => {
          console.log(response.data);
          toast.success(response.data, { position: toast.POSITION.BOTTOM_RIGHT }), {
            autoClose: 1000,
          }
        })
        .catch((error) => {
          console.log(error);
          toast.error("Wrong user", { position: toast.POSITION.BOTTOM_RIGHT }), {
            autoClose: 1000,
          }
          if (error.response) {
            // The server responded with an error status code
            console.log(error.response.data);
            console.log(error.response.status);
            console.log(error.response.headers);
          } else if (error.request) {
            // The request was made but no response was received
            console.log(error.request);
          } else {
            // Something happened in setting up the request that triggered an Error
            console.log("Error", error.message);
          }
        },
    );
      // localStorage.setItem('token', res.data.token)
    },
  },
};
</script>

<style scoped>
.signup {
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

.loginHeader {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 35px;
  line-height: 28px;
  text-align: center;
}
.login1 {
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
</style>
