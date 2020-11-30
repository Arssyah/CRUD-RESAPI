<?php

namespace App\Controllers;

use CodeIgniter\RESTful\ResourceController;

class Mahasiswa extends ResourceController
{
    protected $format       = 'json';
    protected $modelName    = 'App\Models\Mahasiswa_model';

    public function index()
    {
        //return $this->respond($this->model->findAll(), 200);
        $result = $this->model->findAll();
        $response = [
            'status' => 200,
            'error' => false,
            'message' => 'Berhasil mengambil data',
            'data' => $result,
        ];
        return $this->respond($response, 200);
    }

    public function create()
    {
        $validation =  \Config\Services::validation();

        $nim  = $this->request->getPost('nim');
        $nama  = $this->request->getPost('nama');
        $angkatan = $this->request->getPost('angkatan');

        $data = [
            'nim' => $nim,
            'nama' => $nama,
            'angkatan' => $angkatan
        ];

        if ($validation->run($data, 'mahasiswa') == FALSE) {
            $response = [
                'status' => 500,
                'error' => true,
                'message' => $validation->getErrors(),
            ];
            return $this->respond($response, 500);
        } else {
            $simpan = $this->model->insertMahasiswa($data);
            if ($simpan) {
                $response = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Berhasil Nambah Data Mahasiswa',
                ];
                return $this->respond($response, 200);
            }
        }
    }

    public function show($id = NULL)
    {
        $get = $this->model->getMahasiswa($id);
        if ($get) {
            $code = 200;
            $response = [
                'status' => $code,
                'error' => false,
                'message' => 'Data Berhasil Diambil',
                'data' => $get,
            ];
        } else {
            $code = 401;
            $response = [
                'status' => $code,
                'error' => true,
                'message' => 'Not Found',
            ];
        }
        return $this->respond($response, $code);
    }

    public function edit($id = NULL)
    {
        $get = $this->model->getMahasiswa($id);
        if ($get) {
            $code = 200;
            $response = [
                'status' => $code,
                'error' => false,
                'message' => 'Data Berhasil Diambil',
                'data' => $get,
            ];
        } else {
            $code = 401;
            $response = [
                'status' => $code,
                'error' => true,
                'message' => 'Not Found',
            ];
        }
        return $this->respond($response, $code);
    }

    public function update($id = NULL)
    {
        $validation =  \Config\Services::validation();

        $data = $this->request->getRawInput();

        if ($validation->run($data, 'mahasiswa') == FALSE) {

            $response = [
                'status' => 500,
                'error' => true,
                'message' => $validation->getErrors(),
            ];
            return $this->respond($response, 500);
        } else {

            $simpan = $this->model->updateMahasiswa($data, $id);
            if ($simpan) {
                $response = [
                    'status' => 200,
                    'error' => false,
                    'message' => 'Berhasil Update Data Mahasiswa',
                ];
                return $this->respond($response, 200);
            }
        }
    }

    public function delete($id = NULL)
    {
        $data = $this->model->getMahasiswa($id);
        if (empty($data)) { // jika data kosong atau tidak tersedia
            $code = 401;
            $response = [
                'status' => $code,
                'error' => true,
                'message' => 'Not Found',
            ];
        } else { // jika data ditemukan
            $hapus = $this->model->deleteMahasiswa($id);
            if ($hapus) {
                $code = 200;
                $response = [
                    'status' => $code,
                    'error' => false,
                    'message' => 'Succes Menghapus',
                ];
            } else {
                $code = 401;
                $response = [
                    'status' => $code,
                    'error' => true,
                    'message' => 'Gagal Menghapus Data',
                ];
            }
        }
        return $this->respond($response, $code);
    }
}
