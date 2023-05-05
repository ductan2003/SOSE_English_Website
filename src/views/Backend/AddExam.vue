<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <br>


        <form @submit.prevent="AddExam()">
          <h1 class="loginHeader">Admin Exams</h1>

          <br>

          <div class="form-floating mb-3">
            <input class="form-control" id="floatingUsername" placeholder="username" name="username" v-model="title" required>
            <label for="floatingUsername">Title</label>
          </div>

          <div class="form-floating mb-3">
            <input class="form-control" id="floatingPass" placeholder="password" name="password" v-model="category" required>
            <label for="floatingPass" class="form-lable">Category</label>
          </div>

          <div class="form-floating mb-3">
            <input class="form-control" id="floatingPass" placeholder="password" name="password" v-model="year" required>
            <label for="floatingPass" class="form-lable">Year</label>
          </div>

          <div class="form-floating mb-3">
            <input class="form-control" id="floatingPass" placeholder="password" name="password" v-model="description" required>
            <label for="floatingPass" class="form-lable">Description</label>
          </div>

          <div class="form-group">
            <label class="text-form">File Ques</label>
            <input @change="handleFileQues()" ref="fileQues" type="file" class="form" required/>
          </div>

          <div class="form-group">
            <label class="text-form">File Answer</label>
            <input @change="handleFileAns()" ref="file" type="file" class="form" required />
          </div>

          <div class="form-group">
            <label class="text-form">File Image</label>
            <input @change="handleFileImg()" ref="fileImg" type="file" class="form" required />
          </div>

          <div class="d-grid gap-2">
            <button type="submit" class="login1"  >Add Exam</button>
          </div>
          <hr>
          <div class="buttonList">
            <router-link class="signup" style="background: #0d6efd" type="button" to="/admin/examList">Back</router-link>
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

export default {
  data(){
    return {
      title: "",
      category: "",
      description: "",
      year: "",
      fileAnswer: null,
      fileQuestion: null,
      fileImage: null,
    }
  },
  methods: {
    handleFileImg(){
      // this.fileImg.name = this.title;
      this.fileImg = this.$refs.fileImg.files[0];
    },
    handleFileQues(){
      this.fileQues = this.$refs.fileQues.files[0];
    },
    handleFileAns() {
      this.file = this.$refs.file.files[0];
    },
    async AddExam() {
      await axios.post("http://localhost:8019/admin/exams/save", {
        title: this.title,
        category: this.category,
        description: this.description,
        year: this.year,
        fileAnswer: this.file,
        fileQuestion: this.fileQues,
        fileImage: this.fileImg,
      },  {
            headers: {
              'Content-Type': 'multipart/form-data',
            }
          }
      ).then((response) => {
        console.log(response.data);
        toast.success("Saved successfully", { position: toast.POSITION.BOTTOM_RIGHT }), {
          autoClose: 1000,
        }
        // this.title = "",
        //     this.category = "",
        //     this.description = "",
        //     this.year = "",
        //     this.file = null,
        //     this.fileQues = null
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

  /*background: #0d6efd;*/
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
</style>