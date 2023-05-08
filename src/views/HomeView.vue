<template>
  <div class="home">
    <h3 v-if="user">Hi, {{user.firstName}}</h3>
    <h3 v-if="!user">Not log in</h3>
    <!-- banner -->
    <div class="banner">
      <!-- left banner with text, button -->
      <div class="left-banner">

        <div class="text-banner">
          <div class="big-text">
            <div>Exams and Tips</div>
            <div class="green-text">sose</div>
          </div>

          <div class="small-text">
            dont know what to do? then study english with us!
          </div>
        </div>

        <div class="account">
          <router-link class="signup" type="button" to="/register">register</router-link>
        </div>

      </div>

      <div class="right-banner">
        <img src="@/assets/home/Illustration.png" alt="homeImage" width="320" />
      </div>
    </div>

    <!-- Why us -->
    <div class="whyus">
      <div class="whyus-header">
        <div class="text-header">Why us?</div>
      </div>

      <div class="whyus-content">
        <div class="whyus-item">
          <div class="whyus-item-header">
            <div class="whyus-item-icon">
              <img src="@/assets/home/icon1.png" alt="icon1" />
            </div>

            <div class="whyus-item-textheader">
              Tips
            </div>
          </div>

          <div class="whyus-item-content">
            <div class="item-textcontent">
              Useful tips by experts
            </div>
          </div>
        </div>

        <div class="whyus-item">
          <div class="whyus-item-header">
            <div class="whyus-item-icon">
              <img src="@/assets/home/icon2.png" alt="icon" />
            </div>

            <div class="whyus-item-textheader">
              Exams Library
            </div>
          </div>

          <div class="whyus-item-content">
            <div class="item-textcontent">
              Updating latest IELTS exams
            </div>
          </div>
        </div>

        <div class="whyus-item">
          <div class="whyus-item-header">
            <div class="whyus-item-icon">
              <img src="@/assets/home/icon3.png" alt="icon1" />
            </div>

            <div class="whyus-item-textheader">
              Organization
            </div>
          </div>

          <div class="whyus-item-content">
            <div class="item-textcontent">
              Supported by a professional team
            </div>
          </div>
        </div>

      </div>
    </div>

    <!-- Exam Intro -->
    <div class="exam-intro">
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
          <router-link class="signup" type="button" to="/exams">test now</router-link>
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
          <router-link class="signup" type="button" to="/tips">get tip here</router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
export default {
  name: "Home",
  data() {
    return {
      user: null,
    }
  },
  async created() {
    const response = await axios.get("http://localhost:8019/account/{usename}");
    console.log(response);
  }
}
</script>

<style scoped>
.home {
  background: #F5F7FA;
}

.banner {
  display: flex;
  flex-direction: row;
  align-items: center;
  padding: 96px 144px;
  gap: 104px;

  width: auto;
  height: 550px;

  background: #F5F7FA;

  flex: none;
  order: 0;
  align-self: stretch;
  flex-grow: 0;

}

.left-banner {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px;
  gap: 30px;

  width: 657px;
  height: 276px;

  flex: none;
  order: 0;
  flex-grow: 1;
}

.text-banner {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px;
  gap: 16px;

  width: 657px;
  height: 180px;

  flex: none;
  order: 0;
  align-self: stretch;
  flex-grow: 0;
}

.big-text {
  width: 657px;
  height: 152px;

  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 64px;
  line-height: 76px;

  flex: none;
  order: 0;
  align-self: stretch;
  flex-grow: 0;
  text-align: left;
}

.green-text {
  color: #4caf4f;
}

.small-text {
  width: 657px;
  height: 24px;

  font-family: 'Inter';
  font-style: normal;
  font-weight: 400;
  font-size: 16px;
  line-height: 24px;

  color: #717171;

  flex: none;
  order: 1;
  align-self: stretch;
  flex-grow: 0;
  text-align: left;
}

.account {
  margin-left: 0px;
}

.right-banner {
  width: 350px;
  height: 350px;
  flex: none;
  order: 1;
  flex-grow: 0;
}

.whyus {
  display: flex;
  flex-direction: column-reverse;
  align-items: center;
  padding: 0px;
  gap: 16px;

  width: auto;
  height: 350px;

  flex: none;
  order: 2;
  flex-grow: 0;
}

.whyus-header {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  padding: 0px;
  gap: 16px;

  width: auto;
  height: 60px;

  flex: none;
  order: 2;
  flex-grow: 0;
}

.text-header {
  width: 542px;
  height: 60px;
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 36px;
  line-height: 44px;
  text-align: center;
  color: #4D4D4D;
  flex: none;
  order: 0;
  flex-grow: 0;
}

.whyus-content {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  align-items: center;
  padding: 0px 144px;
  gap: 26px;

  width: 1440px;
  height: 280px;

  flex: none;
  order: 1;
  flex-grow: 0;
}

.whyus-item {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 24px 32px;
  gap: 8px;

  width: 299px;
  height: 260px;

  background: #FFFFFF;
  box-shadow: 0px 2px 4px rgba(171, 190, 209, 0.2);
  border-radius: 8px;

  flex: none;
  order: 0;
  flex-grow: 0;
}

.whyus-item-header {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 0px;
  gap: 16px;

  width: 267px;
  height: 144px;

  flex: none;
  order: 0;
  flex-grow: 0;
}

.whyus-item-icon {
  width: 65px;
  height: 56px;

  flex: none;
  order: 0;
  flex-grow: 0;
}

.whyus-item-textheader {
  width: 267px;
  height: 72px;

  font-family: 'Inter';
  font-style: normal;
  font-weight: 700;
  font-size: 28px;
  line-height: 36px;

  text-align: center;

  color: #4D4D4D;

  flex: none;
  order: 1;
  align-self: stretch;
  flex-grow: 0;
}

.whyus-item-content {
  display: flex;
  flex-direction: row;
  align-items: flex-start;
  padding: 0px;
  gap: 8px;

  width: 251px;
  height: 60px;

  flex: none;
  order: 1;
  flex-grow: 0;
}

.item-textcontent {

  width: 251px;
  height: 60px;

  font-family: 'Inter';
  font-style: normal;
  font-weight: 400;
  font-size: 14px;
  line-height: 20px;

  text-align: center;

  color: #717171;

  flex: none;
  order: 0;
  flex-grow: 0;
}

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
