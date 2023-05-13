<template>
  <div class="exam-container">
    <div class="topic-container" >
        <div v-for="ques in exam.questionsList" :key="ques.id" class="paragraph">
          <div class="section" v-if="ques.section">Section {{ques.section}}</div>
          <div v-if="ques.paragraph">{{ques.paragraph}}</div>
        </div>
    </div>

    <div class="question-container">
      <div class="right-side">
        <div class="exam-header">Exam {{this.exam.title}}</div>
        <div v-if="stop" class="point">
          <p>Total correct answer: {{this.point}}</p>
        </div>
        <div v-for="ques in exam.questionsList" :key="ques.id" class="question">
          <div v-if="ques.title"><i>{{ques.title}}</i></div>
          <div v-if="ques.question" class="question-text">{{ques.question}} </div>

          <div v-if="ques.questionId" class="box">
            <div class="idQues">
              <p>{{ques.questionId}}</p>
            </div>
            <div class="answerbox">
              <input type="text" v-model="answer[ques.questionId]"/>
            </div>
          </div>
          <div v-if="check[ques.questionId] && stop && ques.questionId" class="checkTrue"><i>The correct answer is: {{ques.correctAnswer}}</i></div>
          <div v-if="!check[ques.questionId] && stop && ques.questionId" class="checkFalse"><i>The correct answer is: {{ques.correctAnswer}}</i></div>
        </div>
      </div>

    </div>

  </div>

  <div class="clock">
    <div class="countdown">
      <p>{{Math.floor(this.timecount/60)}}:{{this.timecount%60}}</p>
    </div>
    <div class="submit-ans">
      <button class="button" v-if="!stop" type="button" @click="getPoint"><b>Submit</b></button>
      <router-link class="button" v-if="stop" type="button" to="/exams"><b>Back</b></router-link>
    </div>
  </div>

</template>

<script>
import axios from "axios";
import {toast} from "vue3-toastify";
export default {
  data() {
    return {
      exam: null,
      timecount: 1800,
      answer: new Array(15).fill(""),
      check: new Array(15).fill(false),
      stop: false,
      point: 0,
    }
  },
  methods: {
    async getExam() {
      let url = "http://localhost:8019/user/exams/id=" + parseInt(this.$route.params.id);
      const response = await axios.get(url);
      this.exam = response.data;
    },
    countDownTimer () {
      if (this.timecount > 0 && !this.stop) {
        setTimeout(() => {
          this.timecount -= 1
          this.countDownTimer()
        }, 1000)
      }
      if (this.timecount === 0 && !this.stop) {
        this.getPoint();
      }
    },
    async getAns() {
      // console.log(this.answer);\
      this.stop = true;
      this.getPoint();
      let url = "http://localhost:8019/question/eval-exam";
      await axios
          .post(
              url, {examId: this.exam.id, answers: this.answer}, {
                headers: {
                  "Content-Type": "multipart/form-data",
                },
              }
          )
          .then((response) => {
            console.log(response.data);
          })
          .catch((error) => {
            console.log(error);
            toast.error(error.response, { position: toast.POSITION.BOTTOM_RIGHT }),
                {
                  autoClose: 1000,
                };
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
              console.log("Error", error.message);
            }
          });
    },
    getPoint() {
      this.stop = true;
      // console.log(this.exam)
      for(let i = 0; i < 15; i++) {
        if (this.exam.questionsList[i].correctAnswer != null) {
          let data = JSON.stringify(this.exam.questionsList[i].correctAnswer);
          data = data.substring(1, data.length - 1).toUpperCase();
          // console.log(data)
          // console.log(this.answer[i + 1])
          if (data === this.answer[i + 1].toUpperCase()) {
            this.point++;
            this.check[i+1] = true;
          }
        }
      }
      console.log(this.point);
    }

  },
  beforeMount() {
  },
  created() {
    this.stop = false;
    this.getExam();
    this.countDownTimer();
  }
}
</script>

<style scoped>
.exam-container{
  display: flex;
  flex-direction: row;
  margin-bottom: 0px;
  max-height: 630px;
  padding-bottom: 0px;
  gap: 0px;
}
.topic-container{
  max-width: 50%;
  max-height: 630px;
  background: #d7e6dc;
  overflow: auto;
  overflow-y: scroll;
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.section{
  font-family: Inter;
  font-weight: bold;
  font-size: 24px;
  margin-right: 50px;
  margin-bottom: 0px;
  text-align: center;
  /*margin-left: 50px;*/

}
::-webkit-scrollbar {
  width: 10px;
}

::-webkit-scrollbar-thumb{
  background: #cccccc ;
  border-radius: 7px;
  float: right;
}
::-webkit-scrollbar-track{
  background-color: transparent;
}
.paragraph{
  text-align: left;
  font-family: Inter;
  margin-right: 50px;
  margin-left: 50px;
  margin-bottom: 0px;
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.question-container{
  max-width: 100%;
  max-height: 630px;
  display: flex;
  flex-direction: column;
  gap: 10px;
  overflow: auto;
  overflow-y: scroll;
  justify-content: flex-start;
  margin-bottom: 0px;
  padding-bottom: 0px;
}

.exam-header{
  text-align: center;
  font-family: Inter;
  font-weight: bold;
  font-size: 24px;
}

.question{
  display: flex;
  flex-direction: column;
  text-align: left;
  gap: 3px;
}
.question-text{
  margin-left: 5%;
  margin-right: 5%;
}
.answer-text{


}
.right-side{
  display: flex;
  flex-direction: column;
  margin-left: 30px;
  margin-right: 30px;
  gap: 10px;
}
.box{
  display: flex;
  flex-direction: row;
  gap: 15px;
  margin-left: 5%;
  margin-right: 5%;
}
.idQues{
  background: #4CAF4F;
  height: 35px;
  width: 35px;
  box-sizing: border-box;
  border-radius: 30px;
  display: flex;
  align-items: center;
  justify-content: center;

}
.idQues p{
  color: white;
  font-weight: bold;
  font-size: 16px;
  text-align: center;
  align-items: center;
  margin-bottom: 0px;
}
.answerbox {
  box-sizing: border-box;
  height: 35px;
  display: flex;
  align-items: center;
  padding: 5px 10px;
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
.clock{
  height: 46px;
  background: #A5D6A7;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
}
.countdown{
  margin-left: 47.5%;
}
.countdown p{
  font-family: "Inter";
  font-weight: bold;
  font-size: 25px;
  margin-bottom: 0px;
  justify-content: center;
}
.submit-ans{
  margin-right: 5%;
}

.button{
  border-radius: 30px;
  color: black;
  display: flex;
  flex-direction: row;
  align-items: center;
  border: none;

  width: 100px;
  height: 30px;
  justify-content: center;

  background: #D9D9D9;
  text-decoration: none;
  font-family: 'Inter';
}
.checkTrue{
  margin-left: 5%;
  margin-right: 5%;
  color: #66BB69;
}
.checkFalse{
  margin-left: 5%;
  margin-right: 5%;
  color: #E53835;
}
.point{
  width: 34%;
  height: 35px;
  background: #4CAF4F;
  border-radius: 30px;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 33%;
}
.point p{
  margin-bottom: 0px;
  text-align: center;
  align-items: center;
  color: white;
  font-weight: bold;

}
</style>