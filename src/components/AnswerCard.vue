<template>
  <div class="box">
    <div class="idQues">
      <p>{{id.questionId}}</p>
    </div>
      <div class="answerbox">
        <input type="text" v-bind:value="value"
                       v-on:input="$emit('input', $event.target.value)"/>
      </div>

  </div>
</template>

<script>
export default {

  name: "AnswerCard",
  props: ['id', 'value'],
  // props: { value: {
  //     type: String,
  //   },
  //   id: {
  //     type: Array
  //   }
  // },
  data() {
    return {
      userAns: "",
    }
  }
}
</script>

<style scoped>
.box{
  display: flex;
  flex-direction: row;
  gap: 15px;
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

</style>