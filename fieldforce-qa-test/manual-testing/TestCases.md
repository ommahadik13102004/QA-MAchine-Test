# Manual Test Cases — FieldForceConnect

Target: https://test.fieldforceconnect.com/
Tester: Om Mahadik

> Fill in **Actual Result**, **Status (Pass/Fail)**, and the **Bugs** section
> for each module after you actually execute these against the live app.
> The steps/expected results below are the standard cases for each module —
> add/remove rows once you see the real screens.

## 1. Sign Up

| TC ID | Title | Pre-condition | Steps | Test Data | Expected Result | Actual Result | Status |
|---|---|---|---|---|---|---|---|
| SU_01 | Sign up with valid details | User not registered | 1. Open app 2. Click Sign Up 3. Enter valid name/email/password 4. Submit | Valid unique email | Account created, redirected to login/dashboard | | |
| SU_02 | Sign up with already registered email | Email already exists | Fill form with existing email, submit | Existing email | Error: "Email already registered" | | |
| SU_03 | Sign up with empty mandatory fields | - | Leave name/email/password blank, submit | Blank fields | Field-level validation errors shown, form not submitted | | |
| SU_04 | Sign up with invalid email format | - | Enter "abc@", "abc.com", "abc@com" etc. | Invalid email strings | Validation error: "Enter a valid email" | | |
| SU_05 | Sign up with weak/short password | - | Enter password below minimum length/complexity | e.g. "123" | Validation error on password strength | | |
| SU_06 | Sign up with mismatched confirm password (if applicable) | - | Password ≠ Confirm Password | - | Error: "Passwords do not match" | | |
| SU_07 | Sign up with special characters/SQL injection in name field | - | Enter `<script>`, `' OR 1=1--` etc. | - | Input sanitized/rejected, no script execution | | |
| SU_08 | Sign up - mobile number field validation (if present) | - | Enter letters, <10 digits, >10 digits | - | Validation error shown | | |
| SU_09 | Sign up - Terms & Conditions checkbox (if present) | - | Submit without accepting T&C | - | Error/blocked submission | | |

### Field Validations — Sign Up
- Name: required, no numbers/special characters, max length check
- Email: required, valid format, uniqueness check
- Password: required, min length, complexity rule, masked input
- Mobile (if present): required, exactly 10 digits, numeric only

---

## 2. Forgot Password

| TC ID | Title | Pre-condition | Steps | Test Data | Expected Result | Actual Result | Status |
|---|---|---|---|---|---|---|---|
| FP_01 | Reset with valid registered email | Account exists | Click "Forgot Password", enter registered email, submit | Valid email | Reset link/OTP sent, confirmation message shown | | |
| FP_02 | Reset with unregistered email | - | Enter email not in system | Unregistered email | Error message OR generic "if account exists" message (security best practice) | | |
| FP_03 | Reset with empty email field | - | Submit blank form | - | Validation error: "Email required" | | |
| FP_04 | Reset with invalid email format | - | Enter malformed email | e.g. "abc@" | Validation error shown | | |
| FP_05 | Reset link/OTP expiry | Reset requested | Wait past expiry window, then use link/OTP | - | Error: "Link/OTP expired" | | |
| FP_06 | Set new password same as old password | Valid reset flow | Enter old password as new password | - | Accepted or rejected per business rule — verify actual behavior | | |
| FP_07 | New password fields mismatch | Valid reset flow | New password ≠ Confirm new password | - | Error: "Passwords do not match" | | |

### Field Validations — Forgot Password
- Email: required, valid format
- New password: required, min length/complexity, masked
- OTP/token: required, numeric (if OTP), correct length

---

## 3. Sign In with OTP

| TC ID | Title | Pre-condition | Steps | Test Data | Expected Result | Actual Result | Status |
|---|---|---|---|---|---|---|---|
| OTP_01 | Request OTP with valid registered mobile/email | Account exists | Select "Sign in with OTP", enter valid mobile/email, submit | Valid registered value | OTP sent, confirmation shown | | |
| OTP_02 | Request OTP with unregistered mobile/email | - | Enter unregistered value | - | Appropriate error message | | |
| OTP_03 | Login with correct OTP | OTP requested | Enter correct OTP within validity | Valid OTP | Redirected to dashboard | | |
| OTP_04 | Login with incorrect OTP | OTP requested | Enter wrong OTP | Invalid OTP | Error: "Invalid OTP" | | |
| OTP_05 | Login with expired OTP | OTP requested, wait for expiry | Enter OTP after expiry window | - | Error: "OTP expired" | | |
| OTP_06 | Resend OTP | OTP requested | Click "Resend OTP" | - | New OTP sent, old OTP invalidated, resend cooldown enforced | | |
| OTP_07 | OTP field accepts only numeric input | OTP requested | Enter letters/special characters | - | Field rejects non-numeric input | | |
| OTP_08 | Multiple failed OTP attempts | OTP requested | Enter wrong OTP repeatedly | - | Account/attempt lockout or throttling after N tries | | |

### Field Validations — Sign In with OTP
- Mobile/email: required, valid format
- OTP: required, numeric only, fixed length (commonly 4–6 digits), single-use, time-bound

---

## 4. Login

| TC ID | Title | Pre-condition | Steps | Test Data | Expected Result | Actual Result | Status |
|---|---|---|---|---|---|---|---|
| LG_01 | Login with valid credentials | Account exists | Enter valid email/password, click Login | Valid credentials | Redirected to dashboard | | |
| LG_02 | Login with invalid password | Account exists | Enter valid email, wrong password | - | Error: "Invalid credentials" | | |
| LG_03 | Login with unregistered email | - | Enter email not in system | - | Error: "Invalid credentials" (should not reveal whether email exists) | | |
| LG_04 | Login with empty email/password | - | Submit blank form | - | Field-level validation errors | | |
| LG_05 | Password field masks input | - | Type in password field | - | Characters shown as dots/asterisks, show/hide toggle works if present | | |
| LG_06 | Login button disabled until required fields filled (if applicable) | - | Observe button state | - | Verify actual behavior | | |
| LG_07 | Session persists / "Remember Me" (if present) | Valid login | Login with "Remember Me" checked, close & reopen browser | - | Session retained per expected duration | | |
| LG_08 | Multiple failed login attempts | - | Enter wrong password repeatedly | - | Account lockout/CAPTCHA/throttling after N attempts | | |
| LG_09 | Navigation links from Login page | - | Click "Sign Up", "Forgot Password", "Sign in with OTP" | - | Correctly navigates to respective screens | | |

### Field Validations — Login
- Email: required, valid format
- Password: required, masked, min length enforced

---

## Bugs Found

| Bug ID | Module | Title | Steps to Reproduce | Expected | Actual | Severity | Status |
|---|---|---|---|---|---|---|---|
| BUG_01 | | | | | | | Open |

*(Add one row per bug you actually find while executing the above test cases.)*
