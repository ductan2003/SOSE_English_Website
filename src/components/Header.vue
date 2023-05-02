<template>
    <div class="frame">
        <!-- Logo -->
        <img class="logo" src="@/assets/logo.png" alt="logo" />

        <!-- Links -->
        <div class="linkname">
            <div class="linkdefault">
                <a  href="/" class="label1">Home</a>
            </div>

            <div class="linkdefault">
                <a href="/about" class="label1">About</a>
            </div>

            <div class="linkdefault">
                <a href="/exams" class="label1">Exam</a>
            </div>

            <div class="linkdefault">
                <a href="/tips" class="label1">E-Tips</a>
            </div>

            <!-- <div class="linkdefault">
                <a href="/" class="label1">E-Courses</a>
            </div> -->

            <div class="linkdefault">
                <a href="/" class="label1">Contact</a>
            </div>
        </div>

        <!-- Login/Regis -->
        <div class="account">
            <router-link class="login" to="/login">Login</router-link>
            <router-link class="signup" type="button" to="/register">Sign up</router-link>
        </div>
    </div>
</template>

<script setup>
</script>

<style scroped>
.frame {
    align-items: center;
    background-color: var(--neutralsilver);
    display: flex;
    height: 80px;
    padding: 0px 100px;
    /* width: 100%; */
    /* justify-content: space-between; */
    justify-content: center;
    background: #F5F7FA;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
}

.logo {
    height: 24px;
    width: 154px;
}

.linkname {
    align-items: center;
    display: flex;
    gap: 60px;
    justify-content: center;
    margin-left: 220px;
    position: relative;
    width: fit-content;
    text-decoration: none;
    font-family: 'Inter';

}

.linkdefault {
    align-items: center;
    display: flex;
    gap: 18px;
    position: relative;
    width: fit-content;
    font-family: 'Inter';

}

.label1 {
    text-decoration: none;
    color: var(--eerie-black);
    font-weight: 500;
    line-height: 24px;
    margin-top: -1px;
    position: relative;
    white-space: nowrap;
    width: fit-content;

    font-family: var(--font-family-inter);
    letter-spacing: 0px;
    font-style: normal;
    /* font-weight: bold; */
    font-family: 'Inter';
}

.linkdefault:hover {
    color: #4CAF4F;
}

.lable1:hover{
    color: #4CAF4F;
}

.account {
    align-items: center;
    display: flex;
    gap: 14px;
    margin-left: 11%;
    position: relative;
    width: fit-content;
    font-weight: normal;
    filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));
}

.login {
    color: #4CAF4F;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 10px 20px;
    gap: 10px;

    width: fit-content;
    height: 40px;

    background: #F5F7FA;
    border-radius: 6px;

    flex: none;
    order: 0;
    flex-grow: 0;
    text-decoration: none;
    font-family: 'Inter';

}

.signup {
    color: white;
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 10px 20px;
    gap: 10px;

    width: fit-content;
    height: 40px;
    justify-content: center;

    background: #4CAF4F;
    border-radius: 6px;

    flex: none;
    order: 1;
    flex-grow: 0;
    text-decoration: none;
    font-family: 'Inter';

}
</style>
