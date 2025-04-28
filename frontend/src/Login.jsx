import {useState} from 'react';

export default function Login() {
  return (
    <>
      <h1>Login</h1>
      <div>
        <form className="login-form">
          <h1 className='login-header'>Username</h1>
          <input type="text"></input>
          <h1 className='login-header'>Password</h1>
          <input type="password"></input>
        </form>
      </div>
    </>
  )
}
