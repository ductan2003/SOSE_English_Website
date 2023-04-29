<template>
  <div>
    <h2>Hi, test {{ $route.params.id }}</h2>
  </div>
  <TipsCard v-for="tip in tips" :key="tip.id" />
</template>

<script>
import axios from "axios";
import TipsCard from "../components/TipsCard.vue";
export default {
  name: "app",
  components: {
    TipsCard,
  },
  data() {
    return {
      tips: [],
    };
  },
  methods: {
    getTips() {
      axios
        .get("https://jsonplaceholder.typicode.com/posts")
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

  computed: {
    destinationId() {
      return parseInt(this.$route.params.id);
    },
  },
};
</script>
