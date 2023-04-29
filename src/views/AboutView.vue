<!-- <template>
  <div class="pdffile">
    <pdf src="./pdf/netflixdb.pdf" :page="1" />
  </div>  

  <iframe>

  </iframe>
	
</template>

<script>
import pdf from 'pdfvuer';

export default {
	components: {
		pdf,
	},
  data () {
    return {
      page: 1,
      numPages: 0,
      pdfdata: undefined,
      errors: [],
      scale: '50%'
    }
  },
};
</script>

<style scoped>
.pdffile {
  width: 50%;
  height: auto;
}
</style> -->

<template>
  <!-- <div>
    <button @click="getPosts">sos</button>
  </div> -->
  <div v-for="post in posts" :key="post.id">
    <h3>{{ post.id }}.{{ post.title }}</h3>
    <h2>{{ post.id }}</h2>
    <router-link type="button" :to="{ name: 'test', params: { id: post.id } }"
      >click here</router-link
    >
    <hr />
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Testlist",
  data() {
    return {
      posts: [],
    };
  },
  methods: {
    getPosts() {
      axios
        .get("https://jsonplaceholder.typicode.com/posts")
        .then((response) => {
          console.log(response.data);
          this.posts = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  beforeMount() {
    this.getPosts();
  },
  // mounted() {
  //   this.getPosts();
  // },
};
</script>
