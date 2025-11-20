import React, {useState} from 'react'
import API from '../api'
export default function ApplyService(){
  const [serviceType,setServiceType]=useState('Birth Certificate'); const [description,setDescription]=useState('')
  const submit=async e=>{ e.preventDefault(); try{ const citizenId = 1; await API.post(`/api/requests/create/${citizenId}`,{ serviceType, description }); alert('Submitted'); }catch(err){ alert(err.response?.data?.message || err.message) } }
  return (<div className='p-8'><h1 className='text-2xl font-bold'>Apply for Service</h1><form onSubmit={submit} className='mt-4'><select className='p-2 border w-64' value={serviceType} onChange={e=>setServiceType(e.target.value)}><option>Birth Certificate</option><option>Income Certificate</option><option>Residence Certificate</option></select><br/><textarea className='w-full p-2 border mt-3' rows='4' placeholder='Description' value={description} onChange={e=>setDescription(e.target.value)}></textarea><br/><button className='mt-3 bg-blue-600 text-white p-2 rounded'>Submit</button></form></div>)
}
