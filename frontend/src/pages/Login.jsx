import React, {useState} from 'react'
import API from '../api'
import { useNavigate } from 'react-router-dom'

export default function Login(){
  const [email,setEmail]=useState('')
  const [password,setPassword]=useState('')
  const nav = useNavigate()

  const submit=async e=>{
    e.preventDefault()
    try{
      const res = await API.post('/api/auth/login',{ email,password })
      localStorage.setItem('token', res.data.token)
      const user = res.data.user || res.data.data
      if(user && user.role==='ROLE_ADMIN') nav('/admin'); else nav('/citizen')
    }catch(err){ alert(err.response?.data?.message || err.message) }
  }

  return (<div className='min-h-screen flex items-center justify-center bg-gray-50'>
    <form className='bg-white p-8 rounded shadow-md w-96' onSubmit={submit}>
      <h2 className='text-xl font-bold mb-4'>Login</h2>
      <input className='w-full p-2 mb-3 border' placeholder='Email' value={email} onChange={e=>setEmail(e.target.value)} />
      <input type='password' className='w-full p-2 mb-3 border' placeholder='Password' value={password} onChange={e=>setPassword(e.target.value)} />
      <button className='w-full bg-blue-600 text-white p-2 rounded'>Login</button>
      <p className='mt-3 text-sm'>Don't have account? <a className='text-blue-600' href='/register'>Register</a></p>
    </form>
  </div>)
}
