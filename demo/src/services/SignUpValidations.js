import Validations from "./Validations";

export default class SignUpValidations {
  constructor() {
    this.email = email;
    this.password = password;
  }

  checkValidations() {
    let errors = [];

    // Check if email is valid
    if (!Validations.checkEmail(this.email)) {
      errors['email'] = 'Email is not valid';
    }
    // Check if password is valid
    if (!Validations.minLength(this.password, 6)) {
      errors['password'] = 'Password must be at least 6 characters';
    }

    return errors;
  }
}