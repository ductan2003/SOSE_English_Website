<template>
  <div class="container">
    <div class="heading-page">
      <p class="heading-page-content">Admin - Tip</p>
    </div>
    <br>

    <table class="table">
      <thead>
      <tr>
        <th scope="col">Id</th>
        <th scope="col">Title</th>
        <th scope="col">Body</th>
        <th scope="col">Update</th>
        <th scope="col">Delete</th>
      </tr>
      </thead>
      <tbody v-for="tip in tips" :key="tip.id" :tip="tip">
      <tr>
        <th class="counterCell"></th>
        <td>{{tip.title}}</td>
        <td>{{tip.category.name}}</td>
        <td>
          <router-link type="button" :to="{ name: 'editExam', params: { id: tip.id } }">
            <img src="@/assets/button/edit.png" alt="update" />
          </router-link>
        </td>
        <td>
          <button v-on:click="deleteTip(tip.id)" class="deleteButton">
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
export default {
  data() {
    return {
      tips: [],
    };
  },
  methods: {
    getTips() {
      axios
          .get("http://localhost:8019/user/exams/all")
          .then((response) => {
            console.log(response.data);
            this.tips = response.data;
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

    deleteTip(id){
      axios.put("http://localhost:8019/admin/delete/" + id).then((response) => {
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
      })
    }
  },
  beforeMount() {
    this.getTips();
  },

};
</script>

<style scoped>

.heading-page {
  position: relative;
  top: 15px;
  height: 100px;
  align-items: center;
  justify-content: center;
  padding-bottom: 20px;

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
</style>
