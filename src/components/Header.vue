<template>
    <div class="frame">
        <!-- Logo -->
      <router-link to="/">
        <img class="logo" src="@/assets/logo.png" alt="logo"/>
      </router-link>

        <!-- Links -->
      <div v-if="admin" class="linkname">
        <div class="linkdefault">
          <a  href="/" class="label1">Home</a>
        </div>

        <div class="linkdefault">
          <a  href="/admin/summary" class="label1">Summary</a>
        </div>

        <div class="linkdefault">
          <a href="/admin/examList" class="label1">Exam List</a>
        </div>

        <div class="linkdefault">
          <a href="/admin/tipList" class="label1">Tip List</a>
        </div>
<!--      </div>-->

      </div>
        <div v-if="!admin" class="linkname">
            <div class="linkdefault">
                <a  href="/" class="label1">Home</a>
            </div>

            <div class="linkdefault">
                <a href="/about" class="label1">About</a>
            </div>

            <div class="linkdefault">
                <a href="/exams" class="label1">Exam</a>
            </div>

            <div class="linkdefault">
                <a href="/tips" class="label1">E-Tips</a>
            </div>

            <div class="linkdefault">
                <a href="/contact" class="label1">Contact</a>
            </div>
        </div>

        <!-- Login/Regis -->
        <div class="account">
            <router-link v-if="!user" class="login" to="/login">Login</router-link>
            <router-link v-if="!user" class="signup" type="button" to="/register">Sign up</router-link>
            <router-link v-if="user" class="signup" @click="logout()" to="/">Log out</router-link>
          <div v-if="user">
            <router-link type="button" :to="{ name: 'profile', params: { id: this.user.username } }">
              <img v-if="!this.avatar" src="@/assets/person3.jpg" alt="ava" />
              <img v-if="this.avatar" :src='"http://localhost:8019/admin/exams/file/" + this.avatar' alt="ava">
            </router-link>
          </div>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export  default {

  data() {
    return {
      user: null,
      admin: false,
      firstName: "",
      avatar: null
    }
  },

  async created() {
    if (localStorage.getItem("token")) {
      axios.defaults.headers.common[ "Authorization"] = `Bearer ` + localStorage.getItem("token");
      const response = await axios.get("http://localhost:8019/api/auth/profile");
      this.user = response.data;
      await this.getRole();
      const res = await axios.get("http://localhost:8019/account/" + this.user.username);
      this.avatar = res.data.profileImage;
    }
    },
  methods: {
    logout() {
      localStorage.clear();
      this.user = null;
      this.admin = false;
    },
    async getUser() {
      if (localStorage.getItem("token")) {
      axios.defaults.headers.common[ "Authorization"] = `Bearer ` + localStorage.getItem("token");
      const response = await axios.get("http://localhost:8019/account/" + this.user.username);
        console.log(response.data)
        this.user = response.data;
      }
    },
    async getUserInfo() {
      await this.getUser();
      const response = await axios.get("http://localhost:8019/account/" + this.user.username);
      this.firstName = response.data.firstName;
    },
    async getRole() {
      if (this.user.roles[0] === "ROLE_ADMIN") {
        this.admin = true;
      } else this.admin = false;
      // console.log(this.admin);
    }

  },
  async beforeMount() {
    // this.$forceUpdate();
    // await this.getUserInfo();
    // this.getRole();
  }

}
</script>

<style scroped>
.frame {
    align-items: center;
    background-color: var(--neutralsilver);
    display: flex;
    height: 80px;
    padding: 0px 100px;
    /* width: 100%; */
    /* justify-content: space-between; */
    justify-content: center;
    background: #F5F7FA;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.logo {
    height: 60px;
    width: auto;
}

.linkname {
    align-items: center;
    display: flex;
    gap: 60px;
    justify-content: center;
    margin-left: 220px;
    position: relative;
    width: fit-content;
    text-decoration: none;
    font-family: 'Inter';

}

.linkdefault {
    align-items: center;
    display: flex;
    gap: 18px;
    position: relative;
    width: fit-content;
    font-family: 'Inter';

}

.label1 {
    text-decoration: none;
    color: var(--eerie-black);
    font-weight: 500;
    line-height: 24px;
    margin-top: -1px;
    position: relative;
    white-space: nowrap;
    width: fit-content;

    font-family: var(--font-family-inter);
    letter-spacing: 0px;
    font-style: normal;
    /* font-weight: bold; */
    font-family: 'Inter';
}

.linkdefault:hover {
    color: #4CAF4F;
}

.account p {
  margin-top: 0;
  margin-bottom: 0%;
}

.account img {
  width: 50px;
  height: 50px;
  border-radius: 100%;
}

.account {
    align-items: center;
    display: flex;
    gap: 14px;
    margin-left: 11%;
    position: relative;
    width: fit-content;
    font-weight: normal;
    filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
}

.login {
    color: #4CAF4F;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 10px 20px;
    gap: 10px;

    width: fit-content;
    height: 40px;

    background: #F5F7FA;
    border-radius: 6px;

    flex: none;
    order: 0;
    flex-grow: 0;
    text-decoration: none;
    font-family: 'Inter';

}

.signup {
    color: white;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 10px 20px;
    gap: 10px;

    width: fit-content;
    height: 40px;
    justify-content: center;

    background: #4CAF4F;
    border-radius: 6px;

    flex: none;
    order: 1;
    flex-grow: 0;
    text-decoration: none;
    font-family: 'Inter';
}
</style>
