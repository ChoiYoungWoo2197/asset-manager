<template>
  <div class="login-box">
    <!-- /.login-logo -->
    <div class="card card-outline card-primary">
      <div class="card-header text-center">
        <a href="#" class="navbar-brand">
          <img src="@/assets/img/cmt_logo.svg" style="width: 240px; height: 26px;" title="">
        </a>
      </div>
      <div class="card-body">
        <form @submit.prevent="signIn" >
          <div class="input-group mb-3">
            <input type="email" class="form-control" id="email" name="email" placeholder="Email" v-model="email">
            <div class="input-group-append">
              <div class="input-group-text">
                <span class="fas fa-envelope"></span>
              </div>
            </div>
          </div>
          <div class="input-group mb-3">
            <input type="password" class="form-control" id="password" name="password" placeholder="Password" v-model="password">
            <div class="input-group-append">
              <div class="input-group-text">
                <span class="fas fa-lock"></span>
              </div>
            </div>
          </div>
          <div class="row">
            <!-- /.col -->
            <div class="col">
              <button type="submit" class="btn btn-primary btn-block" @click="singIn">로그인</button>
            </div>
            <!-- /.col -->
          </div>
        </form>

        <div class="social-auth-links text-center mt-2 mb-3">
        </div>
        <!-- /.card-body -->
      </div>
      <!-- /.card -->
    </div>
    <!-- /.login-box -->
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: "Login",
  data() {
     return {
       email : '',
       password : '',
     }

  },
  mounted() {
    document.body.classList.add('login-page');
    document.querySelector('.main-header').style.display = 'none';
    document.querySelector('.main-footer').style.display = 'none';
  },
  methods : {
/*    singIn() {
      console.log(this.email, this.password);
/!*      axios.get('http://localhost:8080/logins/sign-in')
          .then(response => {
            console.log('dddddddddddddddddddddddd')
            console.log(response);
            this.$router.replace('Index');
          })
          .catch(e => {
            console.log('error : ', e)          // 에러가 나는 경우 콘솔에 에러를 출력한다
          })*!/

      axios.get('http://localhost:8080/logins/sign-in'), {
        auth: {
          email: this.email,
          password: this.password
        }
      }

          .then(response => {
            console.log('dddddddddddddddddddddddd')
            console.log(response);
            this.$router.replace('Index');
          })
          .catch(e => {
            console.log('error : ', e)          // 에러가 나는 경우 콘솔에 에러를 출력한다
          })
    },*/
    async signIn() {
      try {
        const result = await axios.get('/api/logins', {
          auth: {
            username: this.email,
            password: this.password
          }
        });
        if (result.status === 200) {
          this.loginSuccess = true
        }
      } catch (err) {
        this.loginError = true;
        throw new Error(err)
      }
    }
  }
}
</script>

<style scoped>

</style>