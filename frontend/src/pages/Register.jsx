import React, {useState} from 'react'
import API from '../api'
import { useNavigate } from 'react-router-dom'

export default function Register(){
  const [fullName,setFullName]=useState(''); const [email,setEmail]=useState(''); const [password,setPassword]=useState('')
  const nav = useNavigate()
  const submit=async e=>{ e.preventDefault(); try{ await API.post('/api/auth/register',{ fullName,email,password }); alert('Registered'); nav('/'); }catch(err){ alert(err.response?.data?.message || err.message) } }
  return (<div className='min-h-screen flex items-center justify-center bg-gray-50'>
    <form className='bg-white p-8 rounded shadow-md w-96' onSubmit={submit}>
      <h2 className='text-xl font-bold mb-4'>Register</h2>
      <input className='w-full p-2 mb-3 border' placeholder='Full name' value={fullName} onChange={e=>setFullName(e.target.value)} />
      <input className='w-full p-2 mb-3 border' placeholder='Email' value={email} onChange={e=>setEmail(e.target.value)} />
      <input type='password' className='w-full p-2 mb-3 border' placeholder='Password' value={password} onChange={e=>setPassword(e.target.value)} />
      <button className='w-full bg-green-600 text-white p-2 rounded'>Register</button>
    </form>
  </div>)
}
