<template>
  <div class="container">
    <div class="heading-page">
      <p class="heading-page-content">Admin - Exam</p>
    </div>
    <div class="buttonList">
      <router-link class="signup" type="button" to="/admin/addExam"> + Add new Exam</router-link>
      <form class="search-bar">
        <input class="search-box" type="text" placeholder="Search by title" v-model="search">
        <button type="submit">
          <img src="@/assets/button/search.png" />
        </button>
      </form>
    </div>


    <table class="table">
      <thead>
      <tr>
        <th scope="col">Id</th>
        <th scope="col">Title</th>
        <th scope="col">Category</th>
        <th scope="col">Year</th>
        <th scope="col">Description</th>
        <th scope="col">Update</th>
        <th scope="col">Delete</th>
      </tr>
      </thead>
      <tbody v-for="exam in exams" :key="exam.id" :exam="exam">
      <tr>
        <th class="counterCell"></th>
        <td>{{exam.title}}</td>
        <td>{{exam.category.name}}</td>
        <td>{{exam.year}}</td>
        <td>{{exam.description}}</td>
        <td>
          <router-link type="button" :to="{ name: 'editExam', params: { id: exam.id } }">
            <img src="@/assets/button/edit.png" alt="update" />
          </router-link>
        </td>
        <td>
          <button v-on:click="deleteExam(exam.id)" class="deleteButton">
            <img src="@/assets/button/trash.png" alt="update" />
          </button>

        </td>
      </tr>
      </tbody>
    </table>

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
      exams: [],
      search: "",
    };
  },
  methods: {
    getExams() {
      axios
          .get("http://localhost:8019/user/exams/all")
          .then((response) => {
            console.log(response.data);
            this.exams = response.data;
          })
          .catch((error) => {
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
    deleteExam(id){
      axios.delete("http://localhost:8019/admin/exams/delete/id=" + id).then((response) => {
        console.log(response.data);
        this.getExams();
        toast.success("Deleted successfully", { position: toast.POSITION.BOTTOM_RIGHT }), {
          autoClose: 1000,
        }
      }).catch((error) => {
        console.log(error);
        toast.error("Delete failed", { position: toast.POSITION.BOTTOM_RIGHT }), {
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
      })
    }
  },
  beforeMount() {
    this.getExams();
  },

};
</script>

<style scoped>
.container{
  gap: 20px;
}
.heading-page {
  position: relative;
  top: 15px;
  height: auto;
  align-items: center;
  justify-content: center;
  /*padding-bottom: 20px;*/

  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 64px;
  line-height: 75px;

  color: #4caf4f;
}

.content {
  display: flex;
  flex-direction: row;
}

.deleteButton{
  text-decoration: none;
  border: none;
  background: none;
}

table {
  counter-reset: tableCount;
  font-family: "Inter";
}
.counterCell:before {
  content: counter(tableCount);
  counter-increment: tableCount;
}
.buttonList{
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: flex-end;
  padding-bottom: 2%;
  gap: 10px;
}
.search-bar{
  box-sizing: border-box;
  /*width: 15%;*/
  height: 40px;
  display: flex;
  align-items: center;
  padding: 5px 10px;
  background: #D9DBE1;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
}
.search-bar input{
  background: transparent;
  flex: 1;
  border: 0;
  outline: none;
  padding: 2% 3%;
  font-family: 'Inter';
}
.search-bar button img{
  /*width: 25px;*/
  padding-right: 15px;
}
.search-bar button{
  border: none;
  background: none;
}
.signup{
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}
</style>
