<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <div class="logo-wrapper">
          <img class="logo" src="/static/images/logo.png" />
          <div class="tagline">Open source task management tool</div>
        </div>
        <div v-show="errorMessage" class="alert alert-danger failed">
          {{ errorMessage }}
        </div>
        <form @submit.prevent="submitForm">
          <div class="form-group">
            <label for="username">사용자 이름</label>
            <input
              type="text"
              class="form-control"
              id="username"
              maxlength="50"
              v-model="form.username"
            />
          </div>
          <div
            class="field-error"
            v-if="validationChecker.emailAddress.invalid"
          >
            {{ usernameValidationError }}
          </div>
          <div class="field-error" v-else>
            {{ usernameValidationError }}
          </div>
          <div class="form-group">
            <label for="emailAddress">이메일</label>
            <input
              type="email"
              class="form-control"
              id="emailAddress"
              maxlength="100"
              v-model="form.emailAddress"
            />
            <div
              class="field-error"
              v-if="validationChecker.emailAddress.invalid"
            >
              {{ emailValidationError }}
            </div>
            <div class="field-error" v-else>
              {{ emailValidationError }}
            </div>
          </div>
          <div class="form-group">
            <label for="password">비밀번호</label>
            <input
              type="password"
              class="form-control"
              id="password"
              maxlength="30"
              v-model="form.password"
            />
            <div class="field-error" v-if="validationChecker.password.invalid">
              {{ passwordValidationError }}
            </div>
            <div class="field-error" v-else>
              {{ passwordValidationError }}
            </div>
          </div>
          <div class="d-grid">
            <button
              type="submit"
              :disabled="!isFormValid"
              class="btn btn-primary submit-button"
            >
              계정 생성
            </button>
          </div>
        </form>
      </div>
    </div>
    <footer class="footer">
      <span class="copyright">&copy; 2018 TaskAgile.com</span>
      <ul class="footer-links list-inline float-right">
        <li class="list-inline-item"><a href="#">About</a></li>
        <li class="list-inline-item"><a href="#">Terms of Service</a></li>
        <li class="list-inline-item"><a href="#">Privacy Policy</a></li>
        <li class="list-inline-item">
          <a
            href="https://github.com/taskagile/vuejs.spring-boot.mysql"
            target="_blank"
            >GitHub</a
          >
        </li>
      </ul>
    </footer>
  </div>
</template>
<script>
import registrationService from '@/services/registration/index';

class FormValidationChecker {
  constructor(form) {
    for (let input in form) {
      this[input] = {
        value: form[input],
        invalid: true,
        errors: [],
      };
    }
  }

  isFormValid() {
    for (let prop in this) {
      if (this[prop].invalid === true) {
        return false;
      }
    }
    return true;
  }

  updateUsernameValidation(username) {
    this.username.value = username;
    this.username.errors = [];

    if (username.length > 0) {
      if (username.length < 2) {
        this.username.errors.push('사용자 이름은 2자 이상이어야 합니다.');
      }
      if (username.match(/[^a-zA-Z0-9가-힣ㄱ-ㅎ]/g) != null) {
        this.username.errors.push(
          '사용자 이름은 한글, 영문자, 숫자로만 이루어져야 합니다.'
        );
      }

      if (this.username.errors.length > 0) {
        this.username.invalid = true;
      } else {
        this.username.invalid = false;
      }
    } else {
      this.username.invalid = true;
    }
  }

  updateEmailValidation(email) {
    this.emailAddress.value = email;
    this.emailAddress.errors = [];

    if (email.length > 0) {
      if (email.split('@').length !== 2) {
        this.emailAddress.errors.push('이메일 형식이 올바르지 않습니다.');
      }

      if (this.emailAddress.errors.length > 0) {
        this.emailAddress.invalid = true;
      } else {
        this.emailAddress.invalid = false;
      }
    } else {
      this.emailAddress.invalid = true;
    }
  }

  updatePasswordValidation(password) {
    this.password.value = password;
    this.password.errors = [];

    if (password.length > 0) {
      if (password.length < 6) {
        this.password.errors.push('비밀번호는 6자 이상이어야 합니다.');
      }

      if (this.password.errors.length > 0) {
        this.password.invalid = true;
      } else {
        this.password.invalid = false;
      }
    } else {
      this.password.invalid = true;
    }
  }
}

export default {
  name: 'RegisterPage',
  data() {
    return {
      form: {
        username: '',
        emailAddress: '',
        password: '',
      },
      validationChecker: null,
      errorMessage: '',
    };
  },
  created() {
    this.validationChecker = new FormValidationChecker(this.form);
  },
  methods: {
    async submitForm() {
      try {
        await registrationService.register(this.form);
        this.$router.push({ name: 'LoginPage' });
      } catch (e) {
        this.errorMessage = `회원가입에 실패했습니다. 사유 : ${e.message}`;
      }
    },
  },
  computed: {
    isFormValid() {
      return this.validationChecker.isFormValid();
    },
    usernameValidationError() {
      this.validationChecker.updateUsernameValidation(this.form.username);
      return this.validationChecker.username.errors[0];
    },
    emailValidationError() {
      this.validationChecker.updateEmailValidation(this.form.emailAddress);
      return this.validationChecker.emailAddress.errors[0];
    },
    passwordValidationError() {
      this.validationChecker.updatePasswordValidation(this.form.password);
      return this.validationChecker.password.errors[0];
    },
  },
};
</script>

<style lang="scss" scoped>
.container {
  max-width: 1080px;
}
.register-form {
  margin-top: 50px;
  max-width: 320px;
}
.logo-wrapper {
  text-align: center;
  margin-bottom: 40px;
  .tagline {
    line-height: 180%;
    color: #666;
  }
  .logo {
    max-width: 150px;
    margin: 0 auto;
  }
}
.register-form {
  .form-group label {
    font-weight: bold;
    color: #555;
  }
  .accept-terms {
    margin: 20px 0 40px 0;
  }
}
.footer {
  width: 100%;
  font-size: 13px;
  color: #666;
  line-height: 40px;
  border-top: 1px solid #ddd;
  margin-top: 50px;
  .list-inline-item {
    margin-right: 10px;
  }
  a {
    color: #666;
  }
}
.form-group {
  label {
    text-align: left;
    margin-top: 18px;
    margin-bottom: 6px;
  }
  input {
    margin-bottom: 6px;
  }
}
.submit-button {
  margin-top: 24px;
  padding: 7px 0px;
  font-weight: bold;
}
.field-error {
  padding-left: 4px;
  text-align: left;
  color: red;
  font-size: 14px;
  height: 15px;
}
</style>
