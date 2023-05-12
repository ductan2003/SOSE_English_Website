<template>
  <div v-if="!auth">
    <div class="heading-page">
      <p class="heading-page-content">Please login to view this page</p>
    </div>
  </div>
  <div v-if="auth" class="container">
    <div class="heading-page">
      <p class="heading-page-content">IELTS Mock Tests</p>
    </div>

    <div class="content-page">
      <div class="filter-box">
        <div class="filter-text">FILTERS</div>
        <div class="dropdown">
          <div class="dropdown-text">Type</div>
          <select class="dropdown-box" v-model="typeSelected" type="button" v-on:click="getExams">
            <option class="dropdown-item">Reading</option>
            <option class="dropdown-item">Listening</option>
            <option class="dropdown-item">Writting</option>
            <option class="dropdown-item">Speaking</option>
            <div class="dropdown-pic">
              <img src="@/assets/button/Down.png" />
            </div>
          </select>
        </div>

        <div class="dropdown">
          <div class="dropdown-text">Year</div>
          <select class="dropdown-box" v-model="yearSelected" type="button" v-on:click="getExams">
            <option class="dropdown-item">2023</option>
            <option class="dropdown-item">2022</option>
            <div class="dropdown-pic">
              <img src="@/assets/button/Down.png" />
            </div>
          </select>
        </div>

      </div>

      <div class="exam-list">
        <ExamCard v-for="exam in exams" :key="exam.id" :exam="exam"/>
      </div>
    </div>
  </div>
</template>

<script>
import ExamCard from "@/components/ExamCard";
import axios from "axios";
export default {
  components: {ExamCard},
  name: "Examlist",
  data() {
    return {
      exams: [],
      typeSelected: null,
      yearSelected: null,
      auth: false
    };
  },
  methods: {
    getList(url) {
      axios
          .get(url)
          .then((response) => {
            console.log(response.data);
            this.exams = response.data.listPost;
          })
          .catch((error) => {
            console.log(error);
          });
    },
    getExams() {
      if (!this.yearSelected && this.typeSelected) {
        let url = "http://localhost:8019/user/exams/" + this.typeSelected;
        this.getList(url);
      }
       else if (this.yearSelected && !this.typeSelected) {
        let url = "http://localhost:8019/admin/exams/year=" + this.yearSelected;
        this.getList(url);
        console.log("year")
      }
      else if (this.yearSelected && this.typeSelected) {
        let url = "http://localhost:8019/user/exams/year=" + this.yearSelected + "/" + this.typeSelected;
        this.getList(url);
      } else {
        let url = "http://localhost:8019/user/exams/all";
        this.getList(url);
      }
    },
  },
  beforeMount() {
    this.getExams();
    console.log(this.exams)
    if (localStorage.getItem("token")) {
      this.auth = true;
    }
  },
}

</script>

<style scoped>

.heading-page {
  position: relative;
  top: 15px;
  height: 120px;
  align-items: center;
  justify-content: center;

  font-family: "Inter";
  font-style: normal;
  font-weight: 600;
  font-size: 64px;
  line-height: 75px;
  /* identical to box height, or 119% */

  color: #4caf4f;
}

.content-page {
  align-items: center;
  height: auto;
  position: relative;
  display: flex;
  flex-direction: row;
  width: auto;
  align-items: flex-start;
  margin-left: 8%;
  gap: 4%;
}
.filter-box {
  position: relative;
  width: 251.6px;
  height: 200px;
  /*left: 200px;*/
  /*margin-top: 2%;*/
  /* bottom: auto; */

  background: #c8e6c9;
  border-radius: 30px;
}

.filter-text {
  display: flex;
  flex-direction: column;
  height: 50px;
  justify-content: center;
  font-family: "Inter";
  font-style: normal;
  font-weight: 500;
  font-size: 23px;
  line-height: 40px;
  /* identical to box height, or 174% */

  display: flex;
  align-items: center;
  text-align: center;

  color: #000000;
}
.dropdown {
  display: flex;
  padding-bottom: 60px;
}

.dropdown-text {
  position: absolute;
  left: 10%;

  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 20px;
  line-height: 40px;
  /* or 200% */

  display: flex;
  align-items: center;
  text-align: center;

  color: #000000;
}
.dropdown-box {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  align-items: center;
  position: absolute;
  box-sizing: border-box;
  width: 140.22px;
  height: 40px;
  right: 10%;

  background: #ffffff;
  border-radius: 30px;
  border: 0px;
}

.dropdown-pic {
  height: 22px;
  width: 22px;
  text-align: right;
}

.dropdown-menu {
  background-color: #ffffff;
  width: 140.22px;
}
.dropdown-item {
  background-color: #ffffff;
  width: 140.22px;
  font-family: "Inter";
  font-style: normal;
  text-align: center;
  border-radius: 30px;
}

.exam-list{
  text-align: left;
  top: 3%;
  position: relative;
  height: auto;
}
</style>
