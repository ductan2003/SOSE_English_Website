<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <br>
        <br>

        <form @submit.prevent="AddTip()">
          <h1 class="loginHeader">Admin Tips</h1>

          <br>

          <div class="form-floating mb-3">
            <input class="form-control" id="floatingUsername" placeholder="username" name="username" v-model="title">
            <label for="floatingUsername">Title</label>
          </div>

          <div class="form-floating mb-3">
            <input class="form-control" id="floatingPass" placeholder="password" name="password" v-model="category">
            <label for="floatingPass" class="form-lable">Category</label>
          </div>

          <div class="form-group">
            <label class="text-form">FileQuestion</label>
            <input @change="handleFileQues()" ref="fileQues" type="file" class="form" />
          </div>

          <div class="form-group">
            <label class="text-form">FileAns</label>
            <input @change="handleFileAns()" ref="file" type="file" class="form" />
          </div>

          <div class="d-grid gap-2">
            <button type="submit" class="login1" >Add Tip</button>
<!--            v-on:click="AddTip()"-->
          </div>
          <br>

        </form>
        <br>

      </div>
      <div class="col-md-4"></div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "AddTip",
  data(){
    return {
      title: null,
      category: null,
      fileAnswer: null,
      fileQuestion: null,
    }
  },
  methods: {
    handleFileQues(){
      this.fileQues = this.$refs.fileQues.files[0];
    },
    handleFileAns() {
      this.file = this.$refs.file.files[0];
    },
    async AddTip() {
      await axios.post("http://localhost:8019/admin/save", {
        title: this.title,
        category: this.category,
        fileAnswer: this.file,
        fileQuestion: this.fileQues,
      },  {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          }
      ).then((response) => {
        console.log(response.data);
      }).catch((error) => {
            console.log(error);
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
  },
}
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

  background: #4CAF4F;
  border-radius: 6px;
  text-align: center;

  text-decoration: none;
  font-family: 'Inter';
}

.loginHeader {
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 35px;
  line-height: 28px;
  text-align: center;
}
.login1{
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
</style>