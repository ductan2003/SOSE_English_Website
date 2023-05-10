<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <br>

        <form @submit.prevent="EditExam()">
          <h1 class="loginHeader">Admin - Edit Exam</h1>

          <br>

          <div class="form-floating mb-3">
            <input class="form-control" id="floatingUsername" placeholder="username" name="username" v-model="title">
            <label for="floatingUsername">Title</label>
          </div>

          <div class="form-floating mb-3">
            <input class="form-control" id="floatingPass" placeholder="password" name="password" v-model="category">
            <label for="floatingPass" class="form-lable">Category</label>
          </div>

          <div class="form-floating mb-3">
            <input class="form-control" id="floatingPass" placeholder="password" name="password" v-model="year">
            <label for="floatingPass" class="form-lable">Year</label>
          </div>

          <div class="form-floating mb-3">
            <input class="form-control" id="floatingPass" placeholder="password" name="password" v-model="description">
            <label for="floatingPass" class="form-lable">Description</label>
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
            <button type="submit" class="login1" >Save Exam</button>
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
axios.defaults.headers.common[ "Authorization"] = `Bearer ` + localStorage.getItem("token");

export default {
  data() {
    return {
      exam: [],
      title: "",
      category: "",
      fileAnswer: null,
      fileQuestion: null,
      description: "",
      year: ""
    };
  },
  methods: {
    handleFileQues(){
      this.fileQues = this.fileQuestion,
      this.fileQues = this.$refs.fileQues.files[0];
    },
    handleFileAns() {
      this.file = this.fileAnswer,
      this.file = this.$refs.file.files[0];
    },
    async EditExam() {
      await axios.put("http://localhost:8019/admin/exams/update/id=" + parseInt(this.$route.params.id),{
            title: this.title,
            category: this.category,
            fileAnswer: this.file,
            fileQuestion: this.fileQues,
            description: this.description,
            year: this.year,
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
    getExam() {
      axios
          .get("http://localhost:8019/admin/exams/id=" + parseInt(this.$route.params.id))
          .then((response) => {
            console.log(response.data);
            this.exam = response.data;
            console.log(this.exam.fileAnswer);
            this.title = this.exam.title;
            this.category = this.exam.category.name;
            this.fileAnswer = this.exam.fileAnswer;
            this.fileQuestion = this.exam.fileQuestion;
            this.description = this.exam.description;
            this.year = this.exam.year;
          })
          .catch((error) => {
            console.log(error);
          });
    },
  },
  beforeMount() {
    this.getExam();
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