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
        <div class="exam-header">
          <p>Exam {{this.exam.title}}</p>
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
        </div>
      </div>

    </div>

  </div>

  <div class="clock">
    <div class="countdown">
      <p>{{Math.floor(this.timecount/60)}}:{{this.timecount%60}}</p>
    </div>
    <div class="submit-ans">
      <button type="button" @click="getAns"><b>Submit</b></button>
    </div>
  </div>

</template>

<script>
import axios from "axios";
export default {
  data() {
    return {
      exam: null,
      timecount: 3600,
      answer: new Array(41).fill(""),
    }
  },
  methods: {
    async getExam() {
      let url = "http://localhost:8019/user/exams/id=" + parseInt(this.$route.params.id);
      const response = await axios.get(url);
      this.exam = response.data;
    },
    countDownTimer () {
      if (this.timecount > 0) {
        setTimeout(() => {
          this.timecount -= 1
          this.countDownTimer()
        }, 1000)
      }
      if (this.timecount === 0) {
        this.getAns();
      }
    },
    getAns() {
      console.log(this.answer);
    }

  },
  beforeMount() {
  },
  created() {
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
  display: flex;
  flex-direction: column;
  max-width: 50%;
  margin-left: 50px;
  margin-right: 50px;

}
.exam-header p{
  width: 660px;
  text-align: center;
  font-family: Inter;
  font-weight: bold;
  font-size: 24px;
  margin-right: 50px;
  margin-bottom: 0px;
  text-align: center;
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
.submit-ans button{
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

</style>