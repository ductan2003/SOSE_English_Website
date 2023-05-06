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
  <!-- Exam Intro -->
  <div class="exam-intro" id = "#about">
    <div class="exam-logo">
      <img src="@/assets/home/examIntro.png" alt="exam logo" width="442" />
    </div>

    <div class="exam-text">
      <div class="exam-item-text">
        <div class="exam-header">
          The largest free IELTS exams library for students
        </div>
        <div class="exam-smalltext">
          Our website provide latest IELTS exams. More than 100 IELST reading, listening, writting test updated
          everyweek are waiting for you. We believe that "Practice makes perfect". So join us below.
        </div>
      </div>
      <div class="exambutton">
        <router-link class="signup" type="button" to="/about">test now</router-link>
      </div>
    </div>
  </div>

  <!-- Tip intro -->
  <div class="tip-intro">
    <div class="exam-logo">
      <img src="@/assets/home/tipIntro.png" alt="exam logo" width="442" />
    </div>

    <div class="exam-text">
      <div class="exam-item-text">
        <div class="exam-header">
          Tips by experts
        </div>
        <div class="exam-smalltext">
          Awsome tips are waiting for you. Viết thêm đi cho dài mới đẹp :)
        </div>
      </div>
      <div class="exambutton">
        <router-link class="signup" type="button" to="/">get tip here</router-link>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Testlist",
};
</script>

<style scoped>

.exam-intro {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 0px 144px;
  gap: 96px;

  width: auto;
  height: 400px;

  flex: none;
  order: 0;
  flex-grow: 0;
}

.exam-text {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px;
  gap: 0px;

  width: 661px;
  height: 308px;

  flex: none;
  order: 1;
  flex-grow: 0;
}

.exam-item-text {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px;
  gap: 16px;

  width: 601px;
  height: 224px;


  /* Inside auto layout */

  flex: none;
  order: 0;
  flex-grow: 0;
}

.exam-header {
  width: 601px;
  height: 88px;

  /* Heading/Headline 2 */

  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 36px;
  line-height: 44px;
  color: #4D4D4D;

  flex: none;
  order: 0;
  align-self: stretch;
  flex-grow: 0;
}

.exam-smalltext {
  width: 601px;
  height: 120px;

  /* Body/Regular/Body 3 */

  font-family: 'Inter';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 20px;

  color: #717171;

  flex: none;
  order: 1;
  align-self: stretch;
  flex-grow: 0;
}

.exambutton {
  align-items: center;
  display: flex;
  gap: 14px;
  margin-left: 250px;
  position: relative;
  width: fit-content;
  font-weight: normal;
  filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
}

.tip-intro{
  display: flex;
  flex-direction: row-reverse;
  justify-content: space-between;
  align-items: center;
  padding: 0px 144px;
  gap: 96px;

  width: auto;
  height: 400px;

  flex: none;
  order: 0;
  flex-grow: 0;
}
</style>
