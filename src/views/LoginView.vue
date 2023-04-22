<template>
  <div class="container">
    <div class="row">
      <div class="col-md-4"></div>
      <div class="col-md-4">
        <br>
        <br>

        <form>
          <h1 class="loginHeader">Đăng nhập</h1>

          <br>
          
          <div class="form-floating mb-3">
            <input class="form-control" id="floatingUsername" placeholder="username">
            <label for="floatingUsername">Tên đăng nhập</label>
          </div>

          <div class="form-floating mb-3">
            <input type="password" class="form-control" id="floatingPass" placeholder="password">
            <label for="floatingPass" class="form-lable">Mật khẩu</label>
          </div>

          <div class="d-grid gap-2">
            <button type="submit" class="login1">Đăng nhập</button>
          </div>

          <hr>

          <div>
            <router-link class="signup" type="button" to="/register">Tạo tài khoản mới</router-link>
          </div>

          <br>

        </form>
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
  color: white;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;

  width: inherit;
  height: 40px;

  background: #4CAF4F;
  border-radius: 6px;
  text-align: center;

  text-decoration: none;
  font-family: 'Inter';
}

.loginHeader {
  font-family: 'Inter';
  font-style: normal;
  font-weight: 600;
  font-size: 35px;
  line-height: 28px;
  text-align: center;
}
.login1{
  color: white;
  display: flex;
  flex-direction: row;
  align-items: center;
  justify-content: center;

  width: inherit;
  height: 40px;

  background: #0d6efd;
  border-radius: 6px;
  text-align: center;

  text-decoration: none;
  font-family: 'Inter';
  border-width: 0px;
}
</style>