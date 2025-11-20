import React, {useEffect,useState} from 'react'
import API from '../api'
export default function AdminDashboard(){
  const [requests,setRequests]=useState([])
  useEffect(()=>{ API.get('/api/admin/requests').then(r=>setRequests(r.data)).catch(()=>{}) },[])
  return (<div className='p-8'><h1 className='text-2xl font-bold'>Admin Dashboard</h1><div className='mt-4'><h2 className='font-semibold'>Requests</h2>{requests.length===0?<p>No requests</p>:requests.map(req=>(<div key={req.id} className='border p-3 my-2 rounded'><div><b>{req.serviceType}</b> - {req.trackingId}</div><div>Status: {req.status}</div></div>))}</div></div>)
}
