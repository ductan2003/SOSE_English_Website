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
      <div class="newComment">
        <img src="@/assets/person3.jpg" alt="ava" class="avatar"/>
        <div class="answerbox">
          <input type="text" v-model="newComment" placeholder="Comment Here"/>
          <button type="button" v-on:click="getTips">
            <img src="@/assets/button/Send.png" />
          </button>
        </div>
      </div>
      <div class="oldComment">
<!--        get ava user-->
        <img src="@/assets/person3.jpg" alt="ava" />
        <div class="oldCommentBox">
          <p>A   Open your eyes in sea water and it is difficult to see much more than a murky, bleary green colour. Sounds, too, are garbled and difficult to comprehend. Without specialised equipment humans would be lost in these deep sea habitats, so how do fish make it seem so easy? Much of this is due to a biological phenomenon known as electroreception – the ability to perceive and act upon electrical stimuli as part of the overall senses. This ability is only found in aquatic or amphibious species because water is an efficient conductor of electricity.
          </p>
        </div>
      </div>

    </div>

  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      tip: null,
      newComment: ""
    }
  },
  methods: {
    async getTip() {
      let url = "http://localhost:8019/tips/post/" + parseInt(this.$route.params.id);
      const response = await axios.get(url);
      this.tip = response.data;
    },
    async postComment() {
      let url = "http://localhost:8019/tips//comments/postComment";
      await axios.post(url, {
        comment: this.newComment
      });
    }

  },
  beforeMount() {
    this.getTip();
  }

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
}
.oldComment img{
  width: 40px;
  height: 40px;
  border-radius: 100%;
}
.oldCommentBox{
  background-color: #D9DBE1;
  width: 100%;
  height: auto;
  box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
  border-radius: 30px;
  display: flex;
  align-items: center;
  justify-content: flex-start;
  align-content: center;
}
.oldCommentBox p{
  text-align: left;
  font-family: Inter;
  margin: 2% 3%;
}
</style>