INSERT INTO tbl_permission (id, permission_name) VALUES
                                                     ('874ad3a3-285c-4dbb-b26a-ebc206666ab6', 'SUPER_ADMIN'),
                                                     ('45c956b1-bc54-448d-a0c5-d938a428ccf2', 'ADMIN'),
                                                     ('a1bdee4a-0c66-41db-84ce-21a3858df670', 'MAKE'),
                                                     ('ae4d6cad-45e4-4030-a9eb-07b4dc335885', 'CHECK'),
                                                     ('12862496-5d0d-41cc-bc4a-d86d3189f4c2', 'READ');

INSERT INTO tbl_role (id, role_name) VALUES
                                         ('31626266-3631-3834-2d39-3833652d3439', 'SUPER_ADMIN'),
                                         ('9d4075c3-14d8-4c1a-8493-492f454bc8a9', 'ADMIN'),
                                         ('9aefade0-8de5-41a5-bf31-c2ebcf1f5260', 'USER');

INSERT INTO tbl_role_permission (role_id, permission_id) VALUES
                                                             ('31626266-3631-3834-2d39-3833652d3439', '874ad3a3-285c-4dbb-b26a-ebc206666ab6'),
                                                             ('31626266-3631-3834-2d39-3833652d3439', '45c956b1-bc54-448d-a0c5-d938a428ccf2'),
                                                             ('31626266-3631-3834-2d39-3833652d3439', 'a1bdee4a-0c66-41db-84ce-21a3858df670'),
                                                             ('31626266-3631-3834-2d39-3833652d3439', 'ae4d6cad-45e4-4030-a9eb-07b4dc335885'),
                                                             ('31626266-3631-3834-2d39-3833652d3439', '12862496-5d0d-41cc-bc4a-d86d3189f4c2'),
                                                             ('9d4075c3-14d8-4c1a-8493-492f454bc8a9', '45c956b1-bc54-448d-a0c5-d938a428ccf2'),
                                                             ('9d4075c3-14d8-4c1a-8493-492f454bc8a9', 'a1bdee4a-0c66-41db-84ce-21a3858df670'),
                                                             ('9d4075c3-14d8-4c1a-8493-492f454bc8a9', 'ae4d6cad-45e4-4030-a9eb-07b4dc335885'),
                                                             ('9d4075c3-14d8-4c1a-8493-492f454bc8a9', '12862496-5d0d-41cc-bc4a-d86d3189f4c2'),
                                                             ('9aefade0-8de5-41a5-bf31-c2ebcf1f5260', '12862496-5d0d-41cc-bc4a-d86d3189f4c2'),
                                                             ('9aefade0-8de5-41a5-bf31-c2ebcf1f5260', 'a1bdee4a-0c66-41db-84ce-21a3858df670');
