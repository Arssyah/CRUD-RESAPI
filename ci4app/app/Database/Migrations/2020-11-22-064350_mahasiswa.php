<?php

namespace App\Database\Migrations;

use CodeIgniter\Database\Migration;

class Mahasiswa extends Migration
{
	public function up()
	{
		$this->forge->addField([
			'id'           => [
				'type'              => 'INT',
				'constraint'        => 8,
				'unsigned'          => TRUE,
				'auto_increment'    => TRUE
			],
			'nim'           => [
				'type'              => 'VARCHAR',
				'constraint'        => '15',
			],
			'nama'         => [
				'type'              => 'VARCHAR',
				'constraint'        => '50',
			],
			'angkatan'       => [
				'type'              => 'INT',
				'constraint'        => 4,
				'unsigned'          => TRUE,
			],
		]);
		$this->forge->addKey('id', TRUE);
		$this->forge->createTable('mahasiswa');
	}

	//--------------------------------------------------------------------

	public function down()
	{
		//
	}
}
