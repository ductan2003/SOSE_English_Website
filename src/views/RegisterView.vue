<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <br />
        <br />

        <form v-on:@submit.prevent="Register">
          <h1 class="loginHeader">Đăng ký</h1>
          <br />

          <div class="col-md-12">
            <div class="form-floating mb-3">
              <input
                type="text"
                class="form-control"
                id="floatingFirstName"
                placeholder="Họ"
                v-model="firstName"
                required
              />
              <label for="floatingFirstName">Họ</label>
            </div>
          </div>

          <div class="col-md-12">
            <div class="form-floating mb-3">
              <input
                type="text"
                class="form-control"
                id="floatingLastName"
                placeholder="Tên"
                v-model="lastName"
                required
              />
              <label for="floatingLastName">Tên</label>
            </div>
          </div>

          <div class="col-md-12">
            <div class="form-floating mb-3">
              <input
                type="text"
                class="form-control"
                id="floatingNewUserName"
                placeholder="Tên đăng nhập"
                v-model="userName"
                required
              />
              <label for="floatingNewUserName">Tên đăng nhập</label>
            </div>
          </div>

          <div class="col-12">
            <div class="form-floating mb-3">
              <input
                type="password"
                class="form-control"
                id="floatingNewPass"
                placeholder="Mật khẩu"
                v-model="password"
                required
              />
              <label for="floatingNewPass">Mật khẩu</label>
            </div>
          </div>

          <div class="col-12">
            <div class="form-floating mb-3">
              <input
                type="password"
                class="form-control"
                id="floatingNewRePass"
                placeholder="Nhập lại mật khẩu"
                v-model="rePassword"
                required
              />
              <label for="floatingNewRePass">Nhập lại mật khẩu</label>
            </div>
          </div>

          <div class="col-auto">
            <div class="form-check">
              <input
                type="checkbox"
                class="form-check-input"
                id="Check"
                v-model="check"
                required
              />
              <label class="form-check-label" for="Check"
                >Tôi đồng ý với các điều khoản sử dụng
              </label>
            </div>
          </div>

          <div class="col-12">
            <button type="button" class="signup" v-on:click="Register()">
              <b>Đăng ký</b>
            </button>
          </div>
        </form>
        <br />
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
  data() {
    return {
      firstName: "",
      lastName: "",
      userName: "",
      password: "",
      rePassword: "",
      check: false,
    };
  },
  methods: {
    async Register() {
      // console.log(this.$data);
      let url = "http://localhost:8019/api/auth/signup";
      if (this.check) {
        console.log(this.check),
          await axios
            .post(
              url,
              {
                username: this.userName,
                password: this.password,
                confirmPassword: this.rePassword,
                lastName: this.lastName,
                firstName: this.firstName,
              },
              {
                headers: {
                  "Content-Type": "multipart/form-data",
                },
              }
            )
            .then((response) => {
              console.log(response.data);
              // this.$router.push({ path: "/login" });
            })
            .catch((error) => {
              console.log(error);
              toast.error(error.response.data, { position: toast.POSITION.BOTTOM_RIGHT }), {
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
            });
      } else {
        toast.error("Please accept our terms", { position: toast.POSITION.BOTTOM_RIGHT }), {
          autoClose: 1000,
        }
      }
    },
  },
};
</script>

<style scoped>
.signup {
  width: inherit;
  justify-content: center;
  border-radius: 6px;
  border-width: 0px;
  align-content: center;
}

.loginHeader {
  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 35px;
  line-height: 28px;
  text-align: center;
}

.form-control {
  font-family: "Inter";
  font-style: normal;
}

.form-check-input {
  font-family: "Inter";
  font-style: normal;
}

.form-check {
  padding-bottom: 12px;
  text-align: left;
}
</style>
