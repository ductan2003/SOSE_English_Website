<template>
  <div class="container">
    <div class="heading-page">
      <p class="heading-page-content">Admin - Tips</p>
    </div>
    <br>

    <table class="table">
      <thead>
      <tr>
        <th scope="col">#id</th>
        <th scope="col">Title</th>
        <th scope="col">Update</th>
        <th scope="col">Delete</th>
      </tr>
      </thead>
      <tbody v-for="tip in tips" :key="tip.id" :tip="tip">
      <tr>
        <th scope="row">{{tip.id}}</th>
        <td>{{tip.title}}</td>
        <td>
          <router-link type="button" :to="{ name: 'editTip', params: { id: tip.id } }">
            <img src="@/assets/button/edit.png" alt="update" />
          </router-link>
        </td>
        <td>
          <button class="deleteButton">
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
          .get("http://localhost:8019/admin/all")
          .then((response) => {
            console.log(response.data);
            this.tips = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
    },
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

.table{
  font-family: "Inter";
}
</style>
