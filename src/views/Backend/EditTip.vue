<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <br>

        <form @submit.prevent="EditTip()">
          <h1 class="loginHeader">Admin - Edit Tip</h1>

          <br>

          <div class="form-floating mb-3">
            <input class="form-control" id="floatingUsername" placeholder="username" name="username" v-model="title">
            <label for="floatingUsername">Title</label>
          </div>

          <div class="form-floating mb-3">
            <input class="form-control" id="floatingPass" placeholder="password" name="password" v-model="body">
            <label for="floatingPass" class="form-lable">Body</label>
          </div>

          <div class="form-floating mb-3">
            <input class="form-control" id="floatingPass" placeholder="password" name="password" v-model="description">
            <label for="floatingPass" class="form-lable">Description</label>
          </div>

          <div class="form-group">
            <label class="text-form">Image</label>
            <input @change="handleFileImg()" ref="fileImg" type="file" class="form" />
          </div>

          <div class="d-grid gap-2">
            <button type="submit" class="login1" >Edit tip</button>
          </div>
          <hr>
          <div class="buttonList">
            <router-link class="signup" style="background: #0d6efd" type="button" to="/admin/tipList">Back</router-link>
          </div>

        </form>
        <br>

      </div>
      <div class="col-md-4"></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import { toast } from 'vue3-toastify';
import 'vue3-toastify/dist/index.css';
axios.defaults.headers.common[ "Authorization"] = `Bearer ` + localStorage.getItem("token");

export default {
  data() {
    return {
      tip: [],
      title: "",
      body: "",
      description: "",
      image: null,
    };
  },
  methods: {
    handleFileImg(){
      this.image = this.$refs.fileImg.files[0];
    },
    async EditTip() {
      let url = "http://localhost:8019/tips/post/update/" + parseInt(this.$route.params.id);
      await axios.put(url, {
            title: this.title,
            body: this.body,
            description: this.description,
            image: this.image,
            id: parseInt(this.$route.params.id),
          },  {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          }
      ).then((response) => {
        console.log(response.data);
        toast.success("Saved successfully", { position: toast.POSITION.BOTTOM_RIGHT }), {
          autoClose: 1000,
        }
      }).catch((error) => {
        console.log(error);
        toast.error("Saved failed", { position: toast.POSITION.BOTTOM_RIGHT }), {
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
          console.log('Error', error.message);
        }
      });

    },
    getTip() {
      //get url by id
      let url = "http://localhost:8019/tips/post/" + parseInt(this.$route.params.id);
      axios
          .get(url)
          .then((response) => {
            console.log(response.data);
            this.tip = response.data;
            this.title = this.tip.title;
            this.description = this.tip.description;
            this.body = this.tip.body;
          })
          .catch((error) => {
            console.log(error);
          });
    },
  },
  beforeMount() {
    this.getTip();
  },
}

</script>

<style scoped>
.loginHeader {
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 35px;
  line-height: 28px;
  text-align: center;
  color: #4CAF4F;
}
.login1{
  color: white;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;

  width: inherit;
  height: 40px;

  background: #4CAF4F;
  border-radius: 6px;
  text-align: center;

  text-decoration: none;
  font-family: 'Inter';
  border-width: 0px;
}
.form{
  /*margin-right: 10%;*/
}
.text-form{
  font-family: 'Inter';
  text-align: center;
  align-items: center;
  padding-right: 5%;
}
.form-group{
  display: flex;
  align-items: center;
  flex-direction: row;
  padding-bottom: 20px;
}

.signup {
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
  font-family: 'Inter';
}
</style>