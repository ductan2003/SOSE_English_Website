<template>

  <div class="container">
    <div class="title">
      <p v-if="tip.title">{{tip.title}}</p>
    </div>
    <div class="time">
      <p><i>Timestamp: {{tip.dateCreated}}</i></p>
    </div>
    <div class="img">
      <img :src='"http://localhost:8019/admin/exams/file/" + tip.image'   alt="pic"/>
    </div>
    <div class="body">
      <p>{{tip.body}}</p>
    </div>
    <div class="comment">
      <div v-if="user" class="newComment">
        <img class="oldCmtAva" v-if="this.avatar" :src='"http://localhost:8019/admin/exams/file/" + this.avatar' alt="ava">
        <img class="oldCmtAva" v-if="!this.avatar" src="@/assets/person3.jpg" alt="ava" />
        <div class="answerbox">
          <input type="text" v-model="newComment" placeholder="Comment Here"/>
          <button type="button" v-on:click="postComment">
            <img  src="@/assets/button/Send.png" />
          </button>
        </div>
      </div>
      <div v-for="cmt in this.comments" :key="cmt.id" class="oldComment">
<!--        get ava user-->
        <img v-if="cmt.creatorImage" :src='"http://localhost:8019/admin/exams/file/" + cmt.creatorImage' alt="ava">
        <img v-if="!cmt.creatorImage" src="@/assets/person3.jpg" alt="ava" />
        <div class="oldCommentBox">
<!--          <p>A   Open your eyes in sea water and it is difficult to see much more than a murky, bleary green colour. Sounds, too, are garbled and difficult to comprehend. Without specialised equipment humans would be lost in these deep sea habitats, so how do fish make it seem so easy? Much of this is due to a biological phenomenon known as electroreception – the ability to perceive and act upon electrical stimuli as part of the overall senses. This ability is only found in aquatic or amphibious species because water is an efficient conductor of electricity.-->
<!--          </p>-->
          <div class="userName">{{cmt.creatorName}}</div>
          <p>{{cmt.text}}</p>
        </div>
      </div>

    </div>

  </div>
</template>

<script>
import axios from "axios";
import {toast} from "vue3-toastify";


export default {
  data() {
    return {
      tip: null,
      newComment: "",
      comments: null,
      user: null,
      avatar: null
    }
  },
  methods: {
    async getTip() {
      let url = "http://localhost:8019/tips/post/" + parseInt(this.$route.params.id);
      const response = await axios.get(url);
      this.tip = response.data;
      this.comments = this.tip.comments.slice().reverse();
    },
    async postComment() {
      await axios.post("http://localhost:8019/tips/comments/postComment", {
          text: this.newComment,
          postId: this.$route.params.id,
          creatorName: this.user.username
          },  {
            headers: {
              'Content-Type': 'multipart/form-data',
            }
          }
      ).then((response) => {
        console.log(response.data);
        this.getTip();
        this.newComment = "";
        // toast.success("Saved successfully", { position: toast.POSITION.BOTTOM_RIGHT }), {
        //   autoClose: 1000,
        // }
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
    async getUser() {
      if (localStorage.getItem("token")) {
        axios.defaults.headers.common[ "Authorization"] = `Bearer ` + localStorage.getItem("token");
        const response = await axios.get("http://localhost:8019/account/" + this.user.username);
        console.log(response.data)
        this.user = response.data;
      }
    },

  },
  beforeMount() {
    this.getTip();
  },
  async created() {
    if (localStorage.getItem("token")) {
      axios.defaults.headers.common[ "Authorization"] = `Bearer ` + localStorage.getItem("token");
      const response = await axios.get("http://localhost:8019/api/auth/profile");
      this.user = response.data;
      console.log(this.user)
    }
    const response = await axios.get("http://localhost:8019/account/" + this.user.username);
    this.avatar = response.data.profileImage;
  },

}
</script>

<style>
.container{
  width: 55%;
  margin-top: 15px;
  /*margin-left: 20%;*/
  /*margin-right: 20%;*/
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.title p{
  font-family: Inter;
  font-weight: bold;
  font-size: 28px;
  /*margin-right: 50px;*/
  margin-bottom: 0px;
  text-align: center;
}
.time p{
  text-align: right;
  font-family: Inter;
  /*margin-right: 10%;*/
}
.img img{
  width: 100%;
  border-radius: 30px;
}
.body p{
  font-family: Inter;
  font-size: 20px;
  margin-bottom: 0px;
  text-align: left;
  /*margin-left: 10%;*/
  /*margin-right: 10%;*/
}
.comment{
  display: flex;
  flex-direction: column;
  gap: 15px;
}
.newComment{
  display: flex;
  flex-direction: row;
  gap: 15px;
  margin-right: 0px;
}
.newComment img{
}
.answerbox {
  box-sizing: border-box;
  width: 100%;
  height: 40px;
  display: flex;
  align-items: center;
  /*padding: 5px 10px;*/
  background: #D9DBE1;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
}
.answerbox input{
  background: transparent;
  flex: 1;
  border: 0;
  outline: none;
  padding: 2% 3%;
  font-family: 'Inter';
}
.answerbox button{
  border: none;
  background: none;
}
.answerbox button img{
  /*padding-right: 15px;*/
  margin-right: 15px;
}
.avatar{
  width: 40px;
  height: 40px;
  border-radius: 100%;
}
.oldComment{
  display: flex;
  flex-direction: row;
  gap: 15px;
  margin-right: 0px;
  justify-content: center;
}
.oldComment img{
  width: 45px;
  height: 45px;
  border-radius: 100%;
  margin-top: 1%;
}
.oldCommentBox{
  background-color: #D9DBE1;
  width: 100%;
  height: auto;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: flex-start;
  align-content: flex-start;
}
.oldCommentBox p{
  text-align: left;
  font-family: Inter;
  margin: 1% 3% 2%;
  margin-top: 0%;
}
.userName{
  text-align: left;
  font-family: Inter;
  margin: 2% 3% 0px;
  color: #4CAF4F;
  font-weight: bold;
}
.oldCmtAva {
  width: 45px;
  height: 45px;
  border-radius: 100%;
  margin-top: 1%;

}
</style>