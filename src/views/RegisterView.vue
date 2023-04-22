<template>
    <div class="container">
        <div class="row">
            <div class="col-md-4"></div>
            <div class="col-md-4">
                <br>
                <br>
                <form class="row g-3">
                    <h1 class="loginHeader">Đăng ký</h1>
                    <br>

                    <div class="col-md-6">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" id="floatingFirstName" placeholder="Họ" required>
                            <label for="floatingFirstName">Họ</label>
                        </div>
                    </div>

                    <div class="col-md-6">
                        <div class="form-floating mb-3">
                            <input type='text' class="form-control" id="floatingLastName" placeholder="Tên" required>
                            <label for="floatingLastName">Tên</label>
                        </div>
                    </div>

                    <div class="col-md-12">
                        <div class="form-floating mb-3">
                            <input type='text' class="form-control" id="floatingNewUserName" placeholder="Tên đăng nhập"
                                required>
                            <label for="floatingNewUserName">Tên đăng nhập</label>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-floating mb-3">
                            <input type='password' class="form-control" id="floatingNewPass" placeholder="Mật khẩu"
                                required>
                            <label for="floatingNewPass">Mật khẩu</label>
                        </div>
                    </div>

                    <div class="col-12">
                        <div class="form-floating mb-3">
                            <input type='password' class="form-control" id="floatingNewRePass"
                                placeholder="Nhập lại mật khẩu" required>
                            <label for="floatingNewRePass">Nhập lại mật khẩu</label>
                        </div>
                    </div>

                    <div class="col-auto">
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="Check" required>
                            <label class="form-check-label" for="Check">Tôi đồng ý với các điều
                                khoản sử dụng
                            </label>
                        </div>
                    </div>

                    <div class="col-12">
                        <button type="submit" class="signup">
                            <b>Đăng ký</b></button>


                    </div>
                </form>
                <br>
                <br>
            </div>
            <div class="col-md-4"></div>
        </div>
    </div>
</template>
    
<script>

</script>
    
<style scoped>
.signup {
    width: inherit;
    justify-content: center;
    border-radius: 6px;
    border-width: 0px;
    align-content: center;

}

.loginHeader {
    font-family: 'Inter';
    font-style: normal;
    font-weight: 600;
    font-size: 35px;
    line-height: 28px;
    text-align: center;
}

.form-control{
    font-family: 'Inter';
    font-style: normal;
}

.form-check-input{
    font-family: 'Inter';
    font-style: normal;
}
</style>