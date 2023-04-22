<template>
    <div class="footer">
        <!-- Logo and Info -->
        <div class="info">
            <img class="logo" src="@/assets/logo.png" alt="logo" />
            <div class="company-info">
                <p class="info-content">Copyright @UET</p>
                <p class="info-content">All rights reversed.</p>
            </div>
            <div class="social">
                <a href="https://www.instagram.com/_tanducc/">
                    <img class="icons" src='@/assets/instagram.png' />
                </a>

                <a href="https://www.youtube.com/@BLACKPINK">
                    <img class="icons" src='@/assets/youtube.png' />
                </a>
            </div>
        </div>

        <!-- Link Column -->
        <div class="link">
            <div class="col">
                <div class="headercol">Company</div>
                <div class="linkcol">
                    <a href="/about" class="linkinfo">About us</a>
                    <a href="/about" class="linkinfo">To be continued</a>
                </div>
            </div>
            <div class="col">
                <div class="headercol">Products</div>
                <div class="linkcol">
                    <a href="/login" class="linkinfo">Sign in</a>
                    <a href="/register" class="linkinfo">Sign up</a>
                    <a href="/register" class="linkinfo">To be continued</a>
                </div>
            </div>

            <div class="update">
                <div class="headercol">Contact us</div>
                <div>
                    <p class="abc">Add: University of Engineering and Technology, VNU</p>
                    <p class="abc">Phone: 0123456789</p>
                    <p class="abc">Email: sose@vnu.edu.vn</p>
                </div>

            </div>
        </div>

    </div>
</template>

<script setup>

</script>

<style scoped>
.footer {
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    padding: 64px 165px;
    gap: 125px;

    width: auto;
    height: 328px;

    background: #263238;

    flex: none;
    order: 1;
    flex-grow: 0;
}

.info {
    color: white;
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    padding: 0px;
    gap: 40px;

    width: 350px;
    height: 189.67px;

    flex: none;
    order: 0;
    flex-grow: 0;
}

.company-info {
    display: flex;
    flex-direction: column;
    align-items: center;
    text-align: left;
    padding: 0px;
    gap: 4px;

    width: 350px;
    height: 48px;

    flex: none;
    order: 1;
    align-self: center;
    flex-grow: 0;
}

.social {
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    padding: 0px;
    gap: 16px;

    width: 176px;
    height: 32px;

    flex: none;
    order: 2;
    align-self: flex-start;
    flex-grow: 0;
}

.info-content {
    width: 350px;
    height: 20px;

    font-family: 'Inter';
    font-style: normal;
    font-weight: 400;
    font-size: 14px;
    line-height: 20px;

    color: #F5F7FA;

    flex: none;
    order: 0;
    flex-grow: 0;
}

.logo {
    display: flex;
    flex-direction: row;
    align-items: center;
    padding: 0px;
    gap: 9.89px;

    width: 191px;
    height: 29.67px;

    filter: drop-shadow(0px 4px 4px rgba(0, 0, 0, 0.25));

    flex: none;
    order: 0;
    flex-grow: 0;
}

.link {
    display: flex;
    flex-direction: row;
    align-items: flex-start;
    padding: 0px;
    gap: 30px;

    width: 635px;
    height: 200px;


    /* Inside auto layout */

    flex: none;
    order: 1;
    flex-grow: 0;
}

.col {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    padding: 0px;
    gap: 24px;

    width: 160px;
    height: 200px;

    flex: none;
    order: 0;
    flex-grow: 0;
}

.headercol {
    width: 160px;
    height: 28px;

    font-family: 'Inter';
    font-style: normal;
    font-weight: 600;
    font-size: 20px;
    line-height: 28px;
    text-align: left;

    color: #FFFFFF;

    flex: none;
    order: 0;
    flex-grow: 0;
}

.linkcol {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    padding: 0px;
    gap: 12px;

    width: 160px;
    height: 148px;

    flex: none;
    order: 1;
    flex-grow: 0;
}

.linkinfo {
    text-decoration: none;
    color: white;
    text-align: left;
    font-family: 'Inter';
    font-style: normal;
}

.update {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    padding: 0px;
    gap: 24px;

    width: 400px;
    height: 200px;

    flex: none;
    order: 0;
    flex-grow: 0;
}

.abc{
    text-decoration: none;
    color: white;
    text-align: left;
    margin-bottom: 3.29%;
    font-family: 'Inter';
    font-style: normal;
}
</style>