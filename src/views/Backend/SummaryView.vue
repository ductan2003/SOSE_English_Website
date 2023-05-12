<template>
  <div class="container">
    <div class="heading-page">
      <p class="heading-page-content">Admin - Summary</p>
    </div>
    <div class="item-container">
      <!--Total user-->
      <div class="item">
        <div class="item-header">
          <div class="item-icon">
            <img src="@/assets/home/icon2.png" alt="icon" />
          </div>
          <div class="item-textheader">Total Users</div>
        </div>
        <div class="item-content">
          <div class="item-textcontent">{{totalUsers}}</div>
        </div>
      </div>
      <!--Total exam-->
      <div class="item">
        <div class="item-header">
          <div class="item-icon">
            <img src="@/assets/home/icon2.png" alt="icon" />
          </div>
          <div class="item-textheader">Total Exams</div>
        </div>
        <div class="item-content">
          <div class="item-textcontent">{{totalExams}}</div>
        </div>
      </div>
      <!--Total post-->
      <div class="item">
        <div class="item-header">
          <div class="item-icon">
            <img src="@/assets/home/icon2.png" alt="icon" />
          </div>
          <div class="item-textheader">Total Tips</div>
        </div>
        <div class="item-content">
          <div class="item-textcontent">{{totalTips}}</div>
        </div>
      </div>
      <!--Total comment-->
<!--      <div class="item">-->
<!--        <div class="item-header">-->
<!--          <div class="item-icon">-->
<!--            <img src="@/assets/home/icon2.png" alt="icon" />-->
<!--          </div>-->
<!--          <div class="item-textheader">Comments</div>-->
<!--        </div>-->
<!--        <div class="item-content">-->
<!--          <div class="item-textcontent">{{comments}}</div>-->
<!--        </div>-->
<!--      </div>-->
    </div>

  </div>
</template>

<script>
  import axios from "axios";
  axios.defaults.headers.common[ "Authorization"] = `Bearer ` + localStorage.getItem("token");

  export default {
    data() {
      return {
        totalUsers: "",
        totalExams: "",
        totalTips: "",
        comments: "",
      }
    },
    methods: {
      getList(url) {
        axios
            .get(url)
            .then((response) => {
              // console.log(response.data);
              return response.data.total;
            })
            .catch((error) => {
              console.log(error);
            });
      },
      getTotalExams(){
        let url = "http://localhost:8019/admin/exams/all";
        axios.get(url).then((response) => {
          this.totalExams = response.data.total;
        })
      },
      getTotalUsers(){
        let url = "http://localhost:8019/account/all";
        axios.get(url).then((response) => {
          this.totalUsers = response.data.total;
        })
        // console.log(this.totalUsers);
      },
      getTotalTips(){
        let url = "http://localhost:8019/tips/post/all";
        axios.get(url).then((response) => {
          this.totalTips = response.data.total;
        })
      },
      getTotalComment(){
        let url = "";
        axios.get(url).then((response) => {
          this.totalComments = response.data.total;
        })
      },
    },
    beforeMount() {
      this.getTotalTips();
      this.getTotalUsers();
      this.getTotalExams();
      // this.getTotalComment();
    }
  }
</script>

<style scoped>
.container {
  gap: 20px;
  padding-bottom: 20px;
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
.item-container{
  display: flex;
  flex-direction: row;
  gap: 5%;
  padding-top: 20px;
  padding-bottom: 20px;
  align-items: center;
  justify-content: center;
}
.item {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 24px 32px;
  gap: 8px;

  width: 220px;
  height: 200px;

  background: #f5f7fa;
  box-shadow: 0px 2px 4px rgba(171, 190, 209, 0.2);
  border-radius: 8px;

  flex: none;
  order: 0;
  flex-grow: 0;
}
.item-header {
  display: flex;
  flex-direction: column;
  align-items: center;
}
.item-icon {
  width: 65px;
  height: 56px;

  flex: none;
  order: 0;
  flex-grow: 0;
}

.item-textheader {
  /*width: 267px;*/
  /*height: 72px;*/

  font-family: "Inter";
  font-style: normal;
  font-weight: 700;
  font-size: 24px;
  line-height: 36px;

  text-align: center;

  color: #4d4d4d;

  flex: none;
  order: 1;
  align-self: stretch;
  flex-grow: 0;
}
.item-content {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  padding: 0px;
}
.item-textcontent {
  /*width: 251px;*/
  height: 60px;

  font-family: "Inter";
  font-style: normal;
  font-weight: 400;
  font-size: 40px;
  /*line-height: 20px;*/

  text-align: center;

  color: #717171;
}
</style>
