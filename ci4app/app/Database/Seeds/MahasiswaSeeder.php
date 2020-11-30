<?php

namespace App\Database\Seeds;

class MahasiswaSeeder extends \CodeIgniter\Database\Seeder
{
    public function run()
    {
        $data1 = [
            'nim'     => '18051204010',
            'nama'   => 'Yusuf',
            'angkatan' => '2018'
        ];
        $data2 = [
            'nim'     => '18051204000',
            'nama'   => 'Someone',
            'angkatan' => '2018'
        ];
        $this->db->table('mahasiswa')->insert($data1);
        $this->db->table('mahasiswa')->insert($data2);
    }
}
