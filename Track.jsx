import React, {useState} from 'react'
import API from '../api'
export default function Track(){ const [tid,setTid]=useState(''); const [res,setRes]=useState(null)
  const submit=async e=>{ e.preventDefault(); try{ const r = await API.get(`/api/requests/${tid}`); setRes(r.data); } catch(err){ alert(err.response?.data?.message || err.message) } }
  return (<div className='p-8'><h1 className='text-2xl font-bold'>Track Request</h1><form className='mt-4' onSubmit={submit}><input className='p-2 border w-64' placeholder='Request ID' value={tid} onChange={e=>setTid(e.target.value)} /><button className='ml-3 bg-gray-800 text-white p-2 rounded'>Track</button></form>{res && <div className='mt-4 border p-3'><div>Type: {res.serviceType}</div><div>Status: {res.status}</div></div>}</div>)
}
